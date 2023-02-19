package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.enums.menu.InsertMenuIsSuccess;
import com.Ice.WarningSystem.form.menu.InsertMenuForm;
import com.Ice.WarningSystem.http.HttpResultRewrite;
import com.Ice.WarningSystem.http.ResultUtils;
import com.Ice.WarningSystem.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "MenuController",tags = "菜单管理控制层")
@RestController
@Slf4j
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @ApiOperation(value = "新增菜单")
    @PostMapping("/insertMenu")
    public HttpResultRewrite<?> insertMenu(@RequestBody @Validated InsertMenuForm insertMenuForm){
        try{
            int isSuccess= menuService.insertMenu(insertMenuForm);
            if(isSuccess== InsertMenuIsSuccess.INSERT_MENU_IS_SUCCESS.getCode()){
                return  ResultUtils.success(InsertMenuIsSuccess.INSERT_MENU_IS_SUCCESS.getMsg());
            }else if (isSuccess==InsertMenuIsSuccess.INSERT_MENU_IS_ERROR_CREATE_USER_ID.getCode()){
                return ResultUtils.error(InsertMenuIsSuccess.INSERT_MENU_IS_ERROR_CREATE_USER_ID.getMsg());
            }else if(isSuccess==InsertMenuIsSuccess.INSERT_MENU_IS_ERROR.getCode()){
                return ResultUtils.error(InsertMenuIsSuccess.INSERT_MENU_IS_ERROR.getMsg());
            }else {
                return ResultUtils.error(InsertMenuIsSuccess.INSERT_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            log.error(e.toString());
            return ResultUtils.error(InsertMenuIsSuccess.INSERT_ERROR_EXCEPTION.getMsg());
        }
    }
}
