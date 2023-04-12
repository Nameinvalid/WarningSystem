package com.Ice.WarningSystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "数据库中ice_grade表对应的实体类")
@TableName("ice_grade")
public class IceGrade {
    /**
     * 位置id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;
    /**
     * 起始厚度
     */
    @TableField(value = "src_thickness")
    private String srcThickness;
    /**
     * 最终厚度
     */
    @TableField(value = "dist_thickness")
    private String distThickness;
    /**
     * 覆冰等级
     */
    @TableField(value = "ice_grade")
    private String iceGrade;
    /**
     * 等级颜色
     */
    @TableField(value = "grade_color")
    private String gradeColor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrcThickness() {
        return srcThickness;
    }

    public void setSrcThickness(String srcThickness) {
        this.srcThickness = srcThickness;
    }

    public String getDistThickness() {
        return distThickness;
    }

    public void setDistThickness(String distThickness) {
        this.distThickness = distThickness;
    }

    public String getIceGrade() {
        return iceGrade;
    }

    public void setIceGrade(String iceGrade) {
        this.iceGrade = iceGrade;
    }

    public String getGradeColor() {
        return gradeColor;
    }

    public void setGradeColor(String gradeColor) {
        this.gradeColor = gradeColor;
    }

    @Override
    public String toString() {
        return "IceGrade{" +
                "id=" + id +
                ", srcThickness='" + srcThickness + '\'' +
                ", distThickness='" + distThickness + '\'' +
                ", iceGrade='" + iceGrade + '\'' +
                ", gradeColor='" + gradeColor + '\'' +
                '}';
    }
}
