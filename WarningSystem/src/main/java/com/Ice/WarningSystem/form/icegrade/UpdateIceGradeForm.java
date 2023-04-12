package com.Ice.WarningSystem.form.icegrade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端修改实体类")
public class UpdateIceGradeForm {
    @ApiModelProperty(value = "起始厚度")
    private String srcThickness;
    @ApiModelProperty(value = "终止厚度")
    private String distThickness;
    @ApiModelProperty(value = "覆冰等级",example = "1")
    private String iceGrade;
    @ApiModelProperty(value = "等级颜色",example = "1")
    private String gradeColor;
    @ApiModelProperty(value = "修改人")
    private Long updateUserId;
    @ApiModelProperty(value = "修改的哪一个覆冰等级")
    private Long iceGradeId;

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

    public Long getIceGradeId() {
        return iceGradeId;
    }

    public void setIceGradeId(Long iceGradeId) {
        this.iceGradeId = iceGradeId;
    }

    @Override
    public String toString() {
        return "UpdateIceGradeForm{" +
                "srcThickness='" + srcThickness + '\'' +
                ", distThickness='" + distThickness + '\'' +
                ", iceGrade='" + iceGrade + '\'' +
                ", gradeColor='" + gradeColor + '\'' +
                ", updateUserId=" + updateUserId +
                ", iceGradeId=" + iceGradeId +
                '}';
    }
}
