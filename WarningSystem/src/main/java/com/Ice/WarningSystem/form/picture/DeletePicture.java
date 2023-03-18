package com.Ice.WarningSystem.form.picture;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端删除实体类")
public class DeletePicture {
    @ApiModelProperty(value = "删除者id",example = "123456")
    Long deleteUserId;
    @ApiModelProperty(value = "被删除图片id",example = "123456")
    Long photoId;

    public Long getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(Long deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Override
    public String toString() {
        return "DeletePicture{" +
                "deleteUserId=" + deleteUserId +
                ", photoId=" + photoId +
                '}';
    }
}
