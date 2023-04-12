package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.Position;
import com.Ice.WarningSystem.dao.PositionDao;
import com.Ice.WarningSystem.service.PositionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Resource
    PositionDao positionDao;

    @Override
    public List<Position> findPositionAll(String parentPosition) {
        LambdaQueryWrapper<Position> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Position::getParentPosition,parentPosition);
        wrapper.orderByAsc(Position::getId);
        return positionDao.selectList(wrapper);
    }
}
