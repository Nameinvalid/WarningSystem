package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.IceGrade;
import com.Ice.WarningSystem.form.icegrade.DeleteIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.InsertIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.SelectIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.UpdateIceGradeForm;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.service.IceGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "IceGradeController",tags = "覆冰等级管理控制层")
@RestController
@Slf4j
@RequestMapping("/warning")
public class IceGradeController {

    @Autowired
    IceGradeService iceGradeService;

    @PostMapping("/selectWaringLevel")
    @ApiOperation(value = "查询覆冰厚度等级")
    public HttpResult selectWarningLevel(@RequestBody SelectIceGradeForm selectIceGradeForm){
        List<IceGrade> list=iceGradeService.selectIceGrade(selectIceGradeForm);
        return HttpResult.ok(list);
    }

    @PostMapping("/insertWarningLevel")
    @ApiOperation(value = "添加覆冰等级")
    public HttpResult insertWarningLevel(@RequestBody InsertIceGradeForm insertIceGradeForm){
        int count=iceGradeService.insertIceGrade(insertIceGradeForm);
        if (count==1){
            return HttpResult.ok("添加成功");
        }else {
            return HttpResult.error("添加失败");
        }
    }

    @PutMapping("/updateWaringLevel")
    @ApiOperation(value = "修改覆冰等级")
    public HttpResult updateWarningLevel(@RequestBody UpdateIceGradeForm updateIceGradeForm){
        int count=iceGradeService.updateIceGrade(updateIceGradeForm);
        if (count==1){
            return HttpResult.ok("修改成功");
        }else {
            return HttpResult.error("修改出错");
        }
    }

    @DeleteMapping("/deleteWaringLevel")
    @ApiOperation(value = "删除相应的覆冰等级")
    public HttpResult deleteWarningLevel(@RequestBody DeleteIceGradeForm deleteIceGradeForm){
        int count=iceGradeService.deleteIceGrade(deleteIceGradeForm);
        if (count==1){
            return HttpResult.ok("删除成功");
        }else {
            return HttpResult.error("删除失败");
        }
    }
}
