package com.Ice.WarningSystem.form.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端删除实体类")
public class DeleteUserForm {
    @ApiModelProperty(value = "删除者id",example = "123456")
    Long deleteUserId;
    @ApiModelProperty(value = "被删除者id",example = "123456")
    Long userId;

    public Long getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(Long deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DeleteUserForm{" +
                "deleteUserId=" + deleteUserId +
                ", userId=" + userId +
                '}';
    }
}
