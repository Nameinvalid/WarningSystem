package com.Ice.WarningSystem.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(description = "数据库menu表对应的实体类")
@TableName("menu")
public class Menu {
    /**
     * 菜单id
     */
    @TableField(value = "id",fill = FieldFill.INSERT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 菜单名称
     */
    @TableField( value = "menu_name")
    private String menuName;
    /**
     * 父级菜单id
     */
    @TableField( value = "b_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long bId;
    /**
     * 父级菜单名称
     */
    @TableField( value = "parent_menu_name")
    private String parentMenuName;
    /**
     * 菜单序号
     */
    @TableField(value = "menu_index")
    private Integer menuIndex;
    /**
     * 菜单等级
     */
    @TableField(value = "menu_grade")
    private Integer menuGrade;
    /**
     * 菜单状态：1：启用，0：弃用
     */
    @TableField(value = "type")
    private Integer type;
    /**
     * 菜单创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 菜单修改时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    /**
     * 创建菜单的用户的id
     */
    @TableField(value = "create_user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUserId;
    /**
     * 修改菜单的用户的id
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public void setMenuGrade(Integer menuGrade) {
        this.menuGrade = menuGrade;
    }

    public Integer getMenuGrade() {
        return menuGrade;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public Integer getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(Integer menuIndex) {
        this.menuIndex = menuIndex;
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
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", bId=" + bId +
                ", parentMenuName='" + parentMenuName + '\'' +
                ", menuIndex=" + menuIndex +
                ", menuGrade=" + menuGrade +
                ", type=" + type +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
