package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.IceGrade;
import com.Ice.WarningSystem.dao.IceGradeDao;
import com.Ice.WarningSystem.service.IceGradeService;
import com.Ice.WarningSystem.service.LineColorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LineColorServiceImpl implements LineColorService {

    @Resource
    IceGradeDao iceGradeDao;

    @Override
    public List<String> iceToColor(Double[] ice) {
        //查询标准
        LambdaQueryWrapper<IceGrade> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByAsc(IceGrade::getIceGrade);
        List<IceGrade> iceGradeList=iceGradeDao.selectList(wrapper);
        //循环ice，与标准进行对比
        List<String> list=new ArrayList<>();
        for (Double ice1:ice){
            iceGradeList.forEach(iceGrade -> {
                if (ice1 < Double.parseDouble(iceGrade.getDistThickness()) && ice1 >= Double.parseDouble(iceGrade.getSrcThickness())) {
                    list.add(iceGrade.getGradeColor());
                }
            });
        }
        return list;
    }
}
