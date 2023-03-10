package com.Ice.WarningSystem.form.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端添加角色")
public class InsertRoleForm {
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色描述")
    private String remark;
    @ApiModelProperty(value = "添加者id",example = "111111111")
    private Long updateUserId;

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

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    @Override
    public String toString() {
        return "InsertRoleForm{" +
                "roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
