package com.Ice.WarningSystem.form.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端分页查询菜单实体类")
public class SelectMenuPageForm {
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "菜单等级")
    private Integer menuGrade;
    @ApiModelProperty(value = "页数",example = "1")
    private Long pageNum;
    @ApiModelProperty(value = "大小",example = "20")
    private Long pageSize;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuGrade() {
        return menuGrade;
    }

    public void setMenuGrade(Integer menuGrade) {
        this.menuGrade = menuGrade;
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
        return "SelectMenuPageForm{" +
                "menuName='" + menuName + '\'' +
                ", menuGrade=" + menuGrade +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
