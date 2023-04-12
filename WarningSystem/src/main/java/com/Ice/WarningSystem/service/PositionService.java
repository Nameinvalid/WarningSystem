package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.bean.Position;

import java.util.List;

public interface PositionService {

    List<Position> findPositionAll(String parentPosition);
}
