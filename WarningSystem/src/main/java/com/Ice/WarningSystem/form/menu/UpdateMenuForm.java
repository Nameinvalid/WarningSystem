package com.Ice.WarningSystem.form.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端修改菜单实体类")
public class UpdateMenuForm {
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "父菜单名称")
    private String parentMenuName;
    @ApiModelProperty(value = "父菜单id")
    private Long parentMenuId;
    @ApiModelProperty(value = "菜单等级")
    private Integer menuGrade;
    @ApiModelProperty(value = "菜单id")
    private Long menuId;
    @ApiModelProperty(value = "修改者id")
    private Long updateUserId;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Integer getMenuGrade() {
        return menuGrade;
    }

    public void setMenuGrade(Integer menuGrade) {
        this.menuGrade = menuGrade;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "UpdateMenuForm{" +
                "menuName='" + menuName + '\'' +
                ", parentMenuName='" + parentMenuName + '\'' +
                ", parentMenuId=" + parentMenuId +
                ", menuGrade=" + menuGrade +
                ", menuId=" + menuId +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
