package com.Ice.WarningSystem.form.picture;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端修改照片")
public class UpdatePicture {
    @ApiModelProperty(value = "照片id")
    private Long photoId;
    @ApiModelProperty(value = "照片名称")
    private String photoName;
    @ApiModelProperty(value = "照片描述")
    private String remark;
    @ApiModelProperty(value = "照片url")
    private String url;
    @ApiModelProperty(value = "修改者id",example = "111111111")
    private Long updateUserId;
    @ApiModelProperty(value = "图片拍摄位置")
    private String position;

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UpdatePicture{" +
                "photoId=" + photoId +
                ", photoName='" + photoName + '\'' +
                ", remark='" + remark + '\'' +
                ", url='" + url + '\'' +
                ", updateUserId=" + updateUserId +
                ", position='" + position + '\'' +
                '}';
    }
}
