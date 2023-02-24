package com.Ice.WarningSystem.form.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端删除实体类")
public class DeleteRoleForm {
    @ApiModelProperty(value = "删除者id",example = "123456")
    Long deleteUserId;
    @ApiModelProperty(value = "被删除角色id",example = "123456")
    Long roleId;

    public Long getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(Long deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "DeleteRoleForm{" +
                "deleteUserId=" + deleteUserId +
                ", roleId=" + roleId +
                '}';
    }
}
