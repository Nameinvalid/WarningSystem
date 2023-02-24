package com.Ice.WarningSystem.form.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "前端修改角色信息实体类,由超级管理员修改角色信息")
public class UpdateRoleForm {
    @ApiModelProperty(value = "修改角色的用户id",example = "1617775838361726978")
    private Long updateUserId;
    @ApiModelProperty(value = "被修改角色id",example = "1617796109542039554")
    private Long roleId;
    @ApiModelProperty(value = "状态",example = "是否启用该角色")
    private Integer type;
    @ApiModelProperty(value = "角色名称",example = "管理员")
    private String roleName;
    @ApiModelProperty(value = "描述",example = "超级管理员")
    private String remark;

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UpdateRoleForm{" +
                "updateUserId=" + updateUserId +
                ", roleId=" + roleId +
                ", type=" + type +
                ", roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
