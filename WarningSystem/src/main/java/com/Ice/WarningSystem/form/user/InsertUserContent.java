package com.Ice.WarningSystem.form.user;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "前端添加用户信息")
public class InsertUserContent {
    @ApiModelProperty(value = "用户名",example = "zjw")
    private String username;
    @ApiModelProperty(value = "用户密码",example = "zjw123456")
    private String password;
    @ApiModelProperty(value = "用户手机号",required = true,example = "15030912132")
    private Long phone;
    @ApiModelProperty(value = "用户真实名字",required = true,example = "张家维")
    private String name;
    @ApiModelProperty(value = "用户性别",required = true,example = "男")
    private Integer sex;
    @ApiModelProperty(value = "添加者id",example = "111111111")
    private Long updateUserId;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertUserContent)) return false;
        InsertUserContent that = (InsertUserContent) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getPhone(), getName());
    }

    @Override
    public String toString() {
        return "InsertUserContent{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
