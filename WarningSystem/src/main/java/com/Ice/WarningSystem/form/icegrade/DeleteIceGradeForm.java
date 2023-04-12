package com.Ice.WarningSystem.form.icegrade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端删除实体类")
public class DeleteIceGradeForm {
    @ApiModelProperty(value = "删除人")
    private Long deleteUserId;
    @ApiModelProperty(value = "删除的等级")
    private Long iceGradeId;

    public Long getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(Long deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Long getIceGradeId() {
        return iceGradeId;
    }

    public void setIceGradeId(Long iceGradeId) {
        this.iceGradeId = iceGradeId;
    }

    @Override
    public String toString() {
        return "DeleteIceGradeForm{" +
                "deleteUserId=" + deleteUserId +
                ", iceGradeId=" + iceGradeId +
                '}';
    }
}
