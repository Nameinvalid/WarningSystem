package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.bean.IceGrade;
import com.Ice.WarningSystem.form.icegrade.DeleteIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.InsertIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.SelectIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.UpdateIceGradeForm;

import java.util.List;

public interface IceGradeService {
    List<IceGrade> selectIceGrade(SelectIceGradeForm selectIceGradeForm);

    int insertIceGrade(InsertIceGradeForm insertIceGradeForm);

    int updateIceGrade(UpdateIceGradeForm updateIceGradeForm);

    int deleteIceGrade(DeleteIceGradeForm deleteIceGradeForm);
}
