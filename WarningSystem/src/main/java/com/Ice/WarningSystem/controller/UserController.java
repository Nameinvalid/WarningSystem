package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.enums.user.DeleteUserIsSuccess;
import com.Ice.WarningSystem.enums.user.InsertUserIsSuccess;
import com.Ice.WarningSystem.enums.user.UpdateUserIsSuccess;
import com.Ice.WarningSystem.form.user.DeleteUserForm;
import com.Ice.WarningSystem.form.user.InsertUserContent;
import com.Ice.WarningSystem.form.user.SelectUserPageForm;
import com.Ice.WarningSystem.form.user.UpdateUserForm;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.http.HttpResultRewrite;
import com.Ice.WarningSystem.http.HttpStatus;
import com.Ice.WarningSystem.http.ResultUtils;
import com.Ice.WarningSystem.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName UserController
 * @Author lenovo
 * @create 2022/12/2 0:32
 */
@RestController
@Api(value = "UserController",tags = "用户管理控制层")
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户名的检测
     * @param username
     * @return
     */
    @ApiOperation(value = "用户名检测类")
    @GetMapping("/findUsername")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "待查询的用户名",paramType = "query")
    })
    public HttpResult findUsername(@RequestParam @NotEmpty(message = "待查询的用户名不能为空") String username){
        boolean isUse=userService.findUsername(username);
        if(isUse){
            return HttpResult.ok("用户名可以使用");
        }else {
            return HttpResult.error("用户名已存在");
        }
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/insertUser")
    public HttpResultRewrite<?> insertUser(@RequestBody @Validated InsertUserContent insertUserContent){
        try{
            int isSuccess=userService.insertUser(insertUserContent);
            System.out.println(insertUserContent);
            //InsertUserIsSuccess,里面有枚举类的错误码和错误信息，根据错误码获得相应的错误信息
            if(isSuccess==InsertUserIsSuccess.INSERT_USER_IS_ERROR.getCode()){
                return ResultUtils.error(InsertUserIsSuccess.INSERT_USER_IS_ERROR.getMsg());
            }else if (isSuccess==InsertUserIsSuccess.INSERT_USER_IS_SUCCESS.getCode()){
                return ResultUtils.success(InsertUserIsSuccess.INSERT_USER_IS_SUCCESS.getMsg());
            }else if (isSuccess==InsertUserIsSuccess.INSERT_USER_IS_ERROR_CREATE_USER_ID.getCode()){
                return ResultUtils.error(InsertUserIsSuccess.INSERT_USER_IS_ERROR_CREATE_USER_ID.getMsg());
            }else {
                return ResultUtils.error(InsertUserIsSuccess.INSERT_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            return ResultUtils.error(InsertUserIsSuccess.INSERT_ERROR_EXCEPTION.getMsg());
        }
    }

    @ApiOperation(value = "更改用户信息")
    @PutMapping("/updateUser")
    public HttpResultRewrite<?> updateUser(@RequestBody @Validated UpdateUserForm userForm){
        try{
            int isSuccess= userService.updateUser(userForm);
            //UpdateUserIsSuccess,添加和修改是相同的，所以直接使用UpdateUserIsSuccess这个类即可
            if(isSuccess== UpdateUserIsSuccess.UPDATE_USER_IS_ERROR.getCode()){
                return ResultUtils.error(UpdateUserIsSuccess.UPDATE_USER_IS_ERROR.getMsg());
            }else if (isSuccess==UpdateUserIsSuccess.UPDATE_USER_IS_SUCCESS.getCode()){
                return ResultUtils.success(UpdateUserIsSuccess.UPDATE_USER_IS_SUCCESS.getMsg());
            }else if (isSuccess==UpdateUserIsSuccess.UPDATE_USER_IS_ERROR_ID_REPETITION.getCode()){
                return ResultUtils.error(UpdateUserIsSuccess.UPDATE_USER_IS_ERROR_ID_REPETITION.getMsg());
            }else {
                return ResultUtils.error(UpdateUserIsSuccess.UPDATE_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            return ResultUtils.error(UpdateUserIsSuccess.UPDATE_ERROR_EXCEPTION.getMsg());
        }
    }

    @ApiOperation(value = "删除用户信息")
    @DeleteMapping("/deleteUser")
    public HttpResultRewrite<?> deleteUser(@RequestBody DeleteUserForm deleteUserForm){
        try{
            System.out.println(deleteUserForm);
            int isSuccess= userService.deleteUser(deleteUserForm);
            if(isSuccess== DeleteUserIsSuccess.DELETE_USER_IS_ERROR.getCode()){
                return ResultUtils.error(DeleteUserIsSuccess.DELETE_USER_IS_ERROR.getMsg());
            }else if (isSuccess==DeleteUserIsSuccess.DELETE_USER_IS_SUCCESS.getCode()){
                return ResultUtils.success(DeleteUserIsSuccess.DELETE_USER_IS_SUCCESS.getMsg());
            }else if (isSuccess==DeleteUserIsSuccess.DELETE_USER_IS_ERROR_ID_REPETITION.getCode()){
                return ResultUtils.success(DeleteUserIsSuccess.DELETE_USER_IS_ERROR_ID_REPETITION.getMsg());
            }else {
                return ResultUtils.success(DeleteUserIsSuccess.DELETE_ERROR_UNKNOWN.getMsg());
            }
        }catch (Exception e){
            return ResultUtils.success(DeleteUserIsSuccess.DELETE_ERROR_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/findIPageUser")
    @ApiOperation(value = "用户分页查询")
    public HttpResult findIPageUser(@RequestBody SelectUserPageForm pageForm){
        try{
            IPage<User> page=userService.findIPageUser(pageForm);
            if (page.getSize()==0){
                return HttpResult.ok("无结果",page);
            }else {
                return HttpResult.ok("查询成功",page);
            }
        }catch (Exception e){
            return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员",e);
        }
    }
}
