package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.http.HttpStatus;
import com.Ice.WarningSystem.service.ModelService;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import model.ForecastIceCoverThicknessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(value = "ModelController",tags = "位置管理控制层")
@RestController
@Slf4j
@RequestMapping("/model")
public class ModelController {

    @Autowired
    ModelService modelService;

    @PostMapping("/iceModel")
    @ApiOperation(value = "覆冰厚度预测")
    public HttpResult iceModel(@RequestBody Double[][] weatherCondition){
        try{
            Double[] result= modelService.dataScreening(weatherCondition);
            return HttpResult.ok(result);
        } catch (Exception e){
            return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"模型参数异常，请使用正确模型参数",e);
        }
    }

}
