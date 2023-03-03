package com.Ice.WarningSystem.form.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端添加菜单")
public class InsertMenuForm {
    @ApiModelProperty(value = "父级菜单名称")
    private String parentMenuName;
    @ApiModelProperty(value = "父级菜单id")
    private Long parentMenuId;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "创建菜单id")
    private Long createUserId;
    @ApiModelProperty(value = "菜单等级")
    private Integer menuGrade;
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "创建用户id")
    private Long updateUserId;

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getMenuGrade() {
        return menuGrade;
    }

    public void setMenuGrade(Integer menuGrade) {
        this.menuGrade = menuGrade;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "InsertMenuForm{" +
                "parentMenuName='" + parentMenuName + '\'' +
                ", parentMenuId=" + parentMenuId +
                ", menuName='" + menuName + '\'' +
                ", createUserId=" + createUserId +
                ", menuGrade=" + menuGrade +
                ", roleId=" + roleId +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
