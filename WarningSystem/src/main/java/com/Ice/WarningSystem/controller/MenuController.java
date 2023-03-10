package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.Menu;
import com.Ice.WarningSystem.enums.menu.DeleteMenuIsSuccess;
import com.Ice.WarningSystem.enums.menu.InsertMenuIsSuccess;
import com.Ice.WarningSystem.enums.menu.UpdateMenuIsSuccess;
import com.Ice.WarningSystem.form.menu.DeleteMenuForm;
import com.Ice.WarningSystem.form.menu.InsertMenuForm;
import com.Ice.WarningSystem.form.menu.SelectMenuPageForm;
import com.Ice.WarningSystem.form.menu.UpdateMenuForm;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.http.HttpResultRewrite;
import com.Ice.WarningSystem.http.HttpStatus;
import com.Ice.WarningSystem.http.ResultUtils;
import com.Ice.WarningSystem.service.MenuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            return ResultUtils.error(InsertMenuIsSuccess.INSERT_ERROR_EXCEPTION.getMsg());
        }
    }

    @ApiOperation(value = "菜单分页查询")
    @PostMapping("/findMenuPage")
    public HttpResult findIPageMenu(@RequestBody SelectMenuPageForm pageForm){
        try{
            IPage<Menu> page= menuService.findMenuPage(pageForm);
            if (page.getSize()==0){
                return HttpResult.ok("无结果",page);
            }else {
                return HttpResult.ok("查询成功",page);
            }
        }catch (Exception e){
            return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员",e);
        }
    }
    @ApiOperation(value = "查询父级菜单")
    @GetMapping("/findParentMenu/{menuIndex}")
    public HttpResult findParentMenu(@PathVariable Integer menuIndex){
        //查询父级菜单
        return HttpResult.ok(menuService.findMenuList(menuIndex));
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("/updateMenu")
    public HttpResult updateMenu(@RequestBody UpdateMenuForm menuForm){
        try{
            int isSuccess= menuService.updateMenu(menuForm);
            if (isSuccess==UpdateMenuIsSuccess.UPDATE_MENU_IS_ERROR.getCode()){
                return HttpResult.error(UpdateMenuIsSuccess.UPDATE_MENU_IS_ERROR.getMsg());
            }else if (isSuccess==UpdateMenuIsSuccess.UPDATE_MENU_IS_SUCCESS.getCode()){
                return HttpResult.ok(UpdateMenuIsSuccess.UPDATE_MENU_IS_SUCCESS.getMsg());
            }else if (isSuccess==UpdateMenuIsSuccess.UPDATE_MENU_IS_ERROR_ID_REPETITION.getCode()){
                return HttpResult.error(UpdateMenuIsSuccess.UPDATE_MENU_IS_ERROR_ID_REPETITION.getMsg());
            }else {
                return HttpResult.error(UpdateMenuIsSuccess.UPDATE_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            return HttpResult.error(UpdateMenuIsSuccess.UPDATE_ERROR_EXCEPTION.getMsg());
        }
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/deleteMenu")
    public HttpResult deleteMenu(@RequestBody @Validated DeleteMenuForm menuForm){
        try{
            int isSuccess= menuService.deleteMenu(menuForm);
            if (isSuccess==DeleteMenuIsSuccess.DELETE_MENU_IS_ERROR.getCode()){
                return HttpResult.error(DeleteMenuIsSuccess.DELETE_MENU_IS_ERROR.getMsg());
            }else if (isSuccess==DeleteMenuIsSuccess.DELETE_MENU_IS_SUCCESS.getCode()){
                return HttpResult.ok(DeleteMenuIsSuccess.DELETE_MENU_IS_SUCCESS.getMsg());
            }else if (isSuccess==DeleteMenuIsSuccess.DELETE_MENU_IS_ERROR_ID_REPETITION.getCode()){
                return HttpResult.error(DeleteMenuIsSuccess.DELETE_MENU_IS_ERROR_ID_REPETITION.getMsg());
            }else {
                return HttpResult.error(DeleteMenuIsSuccess.DELETE_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            return HttpResult.error(DeleteMenuIsSuccess.DELETE_ERROR_EXCEPTION.getMsg());
        }
    }
}
