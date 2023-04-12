package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.bean.DataProcessing;
import com.mathworks.toolbox.javabuilder.MWException;

public interface ModelService {
    Double[] iceModel(Double[][] weatherCondition) throws MWException;

    Double[] dataScreening(Double[][] weatherCondition) throws MWException;

    DataProcessing dataStandard(String initialStandard);
}
