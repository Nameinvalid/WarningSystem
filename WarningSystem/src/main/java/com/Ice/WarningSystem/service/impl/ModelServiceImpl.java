package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.DataProcessing;
import com.Ice.WarningSystem.dao.DataProcessingDao;
import com.Ice.WarningSystem.service.ModelService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import model.ForecastIceCoverThicknessModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

@Service
public class ModelServiceImpl implements ModelService {

    @Resource
    DataProcessingDao dataProcessingDao;

    @Override
    public Double[] iceModel(Double[][] weatherCondition) throws MWException {
        Double[] result=new Double[weatherCondition.length];
        ForecastIceCoverThicknessModel forecastIceCoverThicknessModel=new ForecastIceCoverThicknessModel();
        MWNumericArray numericArray=null;
        for (int i = 0; i < weatherCondition.length; i++) {
            if (i==0){
                weatherCondition[i][3]=0.0;
            }else {
                weatherCondition[i][3]=numericArray.getDouble();
            }
            numericArray=(MWNumericArray)forecastIceCoverThicknessModel.model(1, (Object) weatherCondition[i])[0];
            result[i]=numericArray.getDouble();
        }
        return result;
    }

    @Override
    public Double[] dataScreening(Double[][] weatherCondition) throws MWException {
        //在这里对传过来的数据做一些处理
        //一维数据对应的是 温度、风速、湿度、之前的厚度
        //温度在0℃以下，起始风速>1ms,空气湿度>85%
        Double[] result=new Double[weatherCondition.length];
        Arrays.fill(result, 0.0);//初始化赋值0
        DataProcessing dataProcessing=dataStandard("不会覆冰");
        DataProcessing dataProcessingInit=dataStandard("覆冰起始条件");
        int tempData=0;//判断不会覆冰
        int windSpeedData=0;//判断不会覆冰
        int humidityData=0;//判断不会覆冰
        int tempDataInit=0;//判断覆冰起始条件
        int windSpeedInit=0;//判断覆冰起始条件
        int humidityDataInit=0;//判断覆冰起始条件
        for (Double[] doubles : weatherCondition) {
            if (doubles[0] > dataProcessing.getTemp()) {
                //如果数据都在这里那么不进行数据预测，全部置0
                tempData++;
            }else if (doubles[0]<dataProcessingInit.getTemp()){
                tempDataInit++;
            }
            if (doubles[1] > dataProcessing.getWindSpeed()) {
                windSpeedData++;
            }else if (doubles[1]<dataProcessingInit.getWindSpeed()){
                windSpeedInit++;
            }
            if (doubles[2] < dataProcessing.getHumidity()) {
                humidityData++;
            }else if (doubles[2]>dataProcessingInit.getHumidity()){
                humidityDataInit++;
            }
        }
        if (tempData== weatherCondition.length &&windSpeedData== weatherCondition.length && humidityData== weatherCondition.length){
            //绝对不会覆冰
            return result;
        }else if ((float)(tempDataInit/weatherCondition.length)>0.8 && (float)(humidityDataInit/ weatherCondition.length)>0.8 && (float)(windSpeedData/ weatherCondition.length)<0.2){
            //全部达成，很可能会覆冰
            //调用模型，并返回数据
            return iceModel(weatherCondition);
        }else if ((float)(windSpeedData/ weatherCondition.length)>0.8){
            //说明风一直都很大，不可能会覆冰
            return result;
        }else {
            return result;
        }
    }

    @Override
    public DataProcessing dataStandard(String initialStandard) {
        LambdaQueryWrapper<DataProcessing> wrapper=new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(initialStandard)){
            wrapper.eq(DataProcessing::getType,initialStandard);
        }
        //查询覆冰的起始数据标准
        return dataProcessingDao.selectOne(wrapper);
    }
}
