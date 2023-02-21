package com.Ice.WarningSystem.form.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端分页查询实体类")
public class SelectUserPageForm {
    @ApiModelProperty(value = "真实名字",example = "张家维")
    private String name;
    @ApiModelProperty(value = "手机号",example = "15030912132")
    private Long phone;
    @ApiModelProperty(value = "页数",example = "1")
    private Long pageNum;
    @ApiModelProperty(value = "大小",example = "20")
    private Long pageSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "SelectUserPageForm{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
