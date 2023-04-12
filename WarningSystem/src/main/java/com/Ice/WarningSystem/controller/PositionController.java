package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.Position;
import com.Ice.WarningSystem.bean.Role;
import com.Ice.WarningSystem.form.role.SelectRolePageForm;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.http.HttpStatus;
import com.Ice.WarningSystem.service.PositionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "PositionController",tags = "位置管理控制层")
@RestController
@Slf4j
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/findPositionAll/{parentPosition}")
    @ApiOperation(value = "位置全量查询")
    public HttpResult findPositionAll(@PathVariable String parentPosition){
        try{
            List<Position> list=positionService.findPositionAll(parentPosition);
            if (list.isEmpty()){
                return HttpResult.error("查询失败，数据库中暂未导入");
            }else {
                return HttpResult.ok(list);
            }
        }catch (Exception e){
            return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员",e);
        }
    }
}
