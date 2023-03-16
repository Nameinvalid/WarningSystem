package com.Ice.WarningSystem.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(description = "数据库中picture表对应的实体类")
@TableName("picture")
public class Picture {
    /**
     * 图片id
     */
    @TableField( value = "id",fill = FieldFill.INSERT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 照片名称
     */
    @TableField(value = "photo_name")
    private String photoName;
    /**
     * 照片地址
     */
    @TableField(value = "photo_url")
    private String photoUrl;
    /**
     * 照片描述
     */
    @TableField(value = "remark")
    private String remark;
    /**
     * 照片拍摄地址
     */
    @TableField(value = "position")
    private String position;
    /**
     * 照片创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 照片修改时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    /**
     * 创建照片的id
     */
    @TableField(value = "create_user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUserId;
    /**
     * 修改照片的id
     */
    @TableField(value = "update_user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUserId;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", photoName='" + photoName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", position='" + position + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
