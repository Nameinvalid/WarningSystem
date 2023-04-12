package com.Ice.WarningSystem.form.icegrade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端全量查询实体类")
public class SelectIceGradeForm {
    @ApiModelProperty(value = "覆冰等级",example = "1")
    private String iceGrade;

    public void setIceGrade(String iceGrade) {
        this.iceGrade = iceGrade;
    }

    public String getIceGrade() {
        return iceGrade;
    }

    @Override
    public String toString() {
        return "SelectIceGradeForm{" +
                "iceGrade='" + iceGrade + '\'' +
                '}';
    }
}
