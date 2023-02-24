package com.Ice.WarningSystem.form.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端分页查询角色实体类")
public class SelectRolePageForm {
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "描述")
    private String remark;
    @ApiModelProperty(value = "页数",example = "1")
    private Long pageNum;
    @ApiModelProperty(value = "大小",example = "20")
    private Long pageSize;

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
        return "SelectRolePageForm{" +
                "roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
