package com.Ice.WarningSystem.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "前端照片返回实体类")
public class PictureVO {
    @ApiModelProperty(value = "照片id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @ApiModelProperty(value = "照片名称")
    private String photoName;
    @ApiModelProperty(value = "照片地址")
    private String photoUrl;
    @ApiModelProperty(value = "照片描述")
    private String remark;
    @ApiModelProperty(value = "照片拍摄地址")
    private String position;
    @ApiModelProperty(value = "照片修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    @ApiModelProperty(value = "照片修改人id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUserId;
    @ApiModelProperty(value = "修改照片人的名字")
    private String updateUserName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Override
    public String toString() {
        return "PictureVO{" +
                "id=" + id +
                ", photoName='" + photoName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", position='" + position + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId=" + updateUserId +
                ", updateUserName='" + updateUserName + '\'' +
                '}';
    }
}
