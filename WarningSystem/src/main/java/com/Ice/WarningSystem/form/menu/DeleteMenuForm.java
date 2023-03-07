package com.Ice.WarningSystem.form.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端删除实体类")
public class DeleteMenuForm {
    @ApiModelProperty(value = "删除者id",example = "123456")
    Long deleteUserId;
    @ApiModelProperty(value = "被删除菜单id",example = "123456")
    Long menuId;

    public Long getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(Long deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "DeleteMenuForm{" +
                "deleteUserId=" + deleteUserId +
                ", menuId=" + menuId +
                '}';
    }
}
