package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.Role;
import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.enums.role.DeleteRoleIsSuccess;
import com.Ice.WarningSystem.enums.role.InsertRoleIsSuccess;
import com.Ice.WarningSystem.enums.role.UpdateRoleIsSuccess;
import com.Ice.WarningSystem.form.role.DeleteRoleForm;
import com.Ice.WarningSystem.form.role.InsertRoleForm;
import com.Ice.WarningSystem.form.role.SelectRolePageForm;
import com.Ice.WarningSystem.form.role.UpdateRoleForm;
import com.Ice.WarningSystem.form.user.SelectUserPageForm;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.http.HttpStatus;
import com.Ice.WarningSystem.service.RoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "RoleController",tags = "角色控制层")
@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/findRolePage")
    @ApiOperation(value = "角色分页查询")
    public HttpResult findIPageRole(@RequestBody SelectRolePageForm pageForm){
        try{
            IPage<Role> page=roleService.findRolePage(pageForm);
            if (page.getSize()==0){
                return HttpResult.ok("无结果",page);
            }else {
                return HttpResult.ok("查询成功",page);
            }
        }catch (Exception e){
            return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员",e);
        }
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("/insertRole")
    public HttpResult insertRole(@RequestBody @Validated InsertRoleForm roleForm){
        try{
            int isSuccess=roleService.insertRole(roleForm);
            if (isSuccess==InsertRoleIsSuccess.INSERT_ROLE_IS_ERROR.getCode()){
                return HttpResult.error(InsertRoleIsSuccess.INSERT_ROLE_IS_ERROR.getMsg());
            }else if (isSuccess==InsertRoleIsSuccess.INSERT_ROLE_IS_SUCCESS.getCode()){
                return HttpResult.ok(InsertRoleIsSuccess.INSERT_ROLE_IS_SUCCESS.getMsg());
            }else if (isSuccess==InsertRoleIsSuccess.INSERT_ROLE_IS_ERROR_CREATE_USER_ID.getCode()){
                return HttpResult.error(InsertRoleIsSuccess.INSERT_ROLE_IS_ERROR_CREATE_USER_ID.getMsg());
            }else {
                return HttpResult.error(InsertRoleIsSuccess.INSERT_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            return HttpResult.error(InsertRoleIsSuccess.INSERT_ERROR_EXCEPTION.getMsg());
        }
    }

    @ApiOperation(value = "修改角色信息")
    @PutMapping("/updateRole")
    public HttpResult updateRole(@RequestBody @Validated UpdateRoleForm roleForm){
        try{
            int isSuccess=roleService.updateRole(roleForm);
            if (isSuccess==UpdateRoleIsSuccess.UPDATE_ROLE_IS_ERROR.getCode()){
                return HttpResult.error(UpdateRoleIsSuccess.UPDATE_ROLE_IS_ERROR.getMsg());
            }else if (isSuccess==UpdateRoleIsSuccess.UPDATE_ROLE_IS_SUCCESS.getCode()){
                return HttpResult.ok(UpdateRoleIsSuccess.UPDATE_ROLE_IS_SUCCESS.getMsg());
            }else if (isSuccess==UpdateRoleIsSuccess.UPDATE_ROLE_IS_ERROR_ID_REPETITION.getCode()){
                return HttpResult.error(UpdateRoleIsSuccess.UPDATE_ROLE_IS_ERROR_ID_REPETITION.getMsg());
            }else {
                return HttpResult.error(UpdateRoleIsSuccess.UPDATE_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            return HttpResult.error(UpdateRoleIsSuccess.UPDATE_ERROR_EXCEPTION.getMsg());
        }
    }

    @ApiOperation(value = "删除角色信息")
    @DeleteMapping("/deleteRole")
    public HttpResult deleteRole(@RequestBody DeleteRoleForm roleForm){
        try{
            int isSuccess=roleService.deleteRole(roleForm);
            if (isSuccess==DeleteRoleIsSuccess.DELETE_USER_IS_ERROR.getCode()){
                return HttpResult.error(DeleteRoleIsSuccess.DELETE_USER_IS_ERROR.getMsg());
            }else if (isSuccess==DeleteRoleIsSuccess.DELETE_USER_IS_SUCCESS.getCode()){
                return HttpResult.ok(DeleteRoleIsSuccess.DELETE_USER_IS_SUCCESS.getMsg());
            }else if (isSuccess==DeleteRoleIsSuccess.DELETE_USER_IS_ERROR_ID_REPETITION.getCode()){
                return HttpResult.error(DeleteRoleIsSuccess.DELETE_USER_IS_ERROR_ID_REPETITION.getMsg());
            }else {
                return HttpResult.error(DeleteRoleIsSuccess.DELETE_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            return HttpResult.error(DeleteRoleIsSuccess.DELETE_ERROR_EXCEPTION.getMsg());
        }
    }
}
