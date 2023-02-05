package com.Ice.WarningSystem.form.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端登录实体类")
public class SelectLogin {
    @ApiModelProperty(value = "用户名",required = true,example = "zjw")
    private String username;
    @ApiModelProperty(value = "密码",required = true,example = "zjw123456")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
