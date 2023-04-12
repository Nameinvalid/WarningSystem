package com.Ice.WarningSystem.form.icegrade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端全量添加实体类")
public class InsertIceGradeForm {
    @ApiModelProperty(value = "起始厚度")
    private String srcThickness;
    @ApiModelProperty(value = "终止厚度")
    private String distThickness;
    @ApiModelProperty(value = "覆冰等级",example = "1")
    private String iceGrade;
    @ApiModelProperty(value = "等级颜色",example = "1")
    private String gradeColor;
    @ApiModelProperty(value = "添加人")
    private Long updateUserId;

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

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "InsertIceGradeForm{" +
                "srcThickness='" + srcThickness + '\'' +
                ", distThickness='" + distThickness + '\'' +
                ", iceGrade='" + iceGrade + '\'' +
                ", gradeColor='" + gradeColor + '\'' +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
