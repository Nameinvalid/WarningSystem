package com.Ice.WarningSystem.form.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端修改用户信息实体类,由超级管理员修改用户信息")
public class UpdateUserForm {
    @ApiModelProperty(value = "修改用户的用户id",example = "1617775838361726978")
    private Long updateUserId;
    @ApiModelProperty(value = "被修改用户的用户id",example = "1617796109542039554")
    private Long userId;
    @ApiModelProperty(value = "用户名",example = "zjw")
    private String username;
    @ApiModelProperty(value = "用户密码",example = "zjw123456")
    private String password;
    @ApiModelProperty(value = "用户手机号",example = "15030912132")
    private Long phone;
    @ApiModelProperty(value = "用户真实名字",example = "张家维")
    private String name;
    @ApiModelProperty(value = "用户账号是否启用",example = "1/0")
    private Integer type;
    @ApiModelProperty(value = "用户性别",example = "男")
    private Integer sex;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Long getPhone() {
        return phone;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return "UpdateUserForm{" +
                "updateUserId=" + updateUserId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", sex=" + sex +
                '}';
    }
}
