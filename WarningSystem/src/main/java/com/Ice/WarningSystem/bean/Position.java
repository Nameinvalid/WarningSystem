package com.Ice.WarningSystem.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;


@ApiModel(description = "数据库中position表对应的实体类")
@TableName("position")
public class Position {
    /**
     * 位置id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type=IdType.AUTO)
    private Long id;
    /**
     * 起始位置名称
     */
    @TableField(value = "position")
    private String position;
    /**
     * 最终位置名称
     */
    @TableField(value = "dist_position")
    private String distPosition;
    /**
     * 所属城区
     */
    @TableField(value = "parent_position")
    private String parentPosition;
    /**
     * 所属地区
     */
    @TableField(value = "father_position")
    private String fatherPosition;
    /**
     * 覆冰厚度
     */
    @TableField(value = "ice")
    private String ice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public String getParentPosition() {
        return parentPosition;
    }

    public String getDistPosition() {
        return distPosition;
    }

    public void setDistPosition(String distPosition) {
        this.distPosition = distPosition;
    }

    public void setParentPosition(String parentPosition) {
        this.parentPosition = parentPosition;
    }

    public String getFatherPosition() {
        return fatherPosition;
    }

    public void setFatherPosition(String fatherPosition) {
        this.fatherPosition = fatherPosition;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", distPosition='" + distPosition + '\'' +
                ", parentPosition='" + parentPosition + '\'' +
                ", fatherPosition='" + fatherPosition + '\'' +
                ", ice='" + ice + '\'' +
                '}';
    }
}
