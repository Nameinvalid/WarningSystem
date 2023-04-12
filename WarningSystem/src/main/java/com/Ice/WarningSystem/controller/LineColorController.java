package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.IceGrade;
import com.Ice.WarningSystem.form.icegrade.SelectIceGradeForm;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.service.IceGradeService;
import com.Ice.WarningSystem.service.LineColorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Api(value = "LineColorController",tags = "线路颜色控制层")
@RestController
@Slf4j
@RequestMapping("/lineColor")
public class LineColorController {

    @Autowired
    LineColorService lineColorService;

    @PostMapping("/iceToColor")
    @ApiOperation(value = "冰厚转换成覆冰等级")
    public HttpResult iceToColor(@RequestBody Double[] ice ){
        List<String> list=lineColorService.iceToColor(ice);
        return HttpResult.ok(list);
    }
}
