package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.form.login.SelectLogin;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.http.HttpResultRewrite;
import com.Ice.WarningSystem.http.ResultUtils;
import com.Ice.WarningSystem.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "LoginController",tags = "登录管理控制层")
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "获取用户的登录密码，与数据库中的密码相匹配")
    @PostMapping("/login")
    public HttpResultRewrite<?> login(@RequestBody SelectLogin selectLogin){
        //int code=loginService.findPassWord(selectLogin);
        User user=loginService.findPassWord(selectLogin);
        if(user!=null){
            if(user.getType()==1){
                //1:用户名存在且用户密码正确
                if(user.getPassword().equals(selectLogin.getPassword())){
                    return ResultUtils.success("密码正确",user);
                }else {
                    //-1：密码错误
                    return ResultUtils.error("密码错误");
                }
            }else {
                //2:账户已经注销，请联系管理员恢复账户
                return ResultUtils.error("账户已经注销，请联系管理员恢复账户,或者重新注册账户");
            }
        }else {
            //用户名不存在
            return ResultUtils.error("用户名不存在");
        }
        /*if (code==0){
            return HttpResult.error("用户名不存在");
        }else if (code==1){
            return HttpResult.ok("密码正确",true);
        }else if (code==2){
            return HttpResult.error("账户已经注销，请联系管理员恢复账户,或者重新注册账户");
        }else if (code==-1){
            return HttpResult.error("密码错误");
        }else {
            return HttpResult.error("未知错误");
        }*/
    }
}
