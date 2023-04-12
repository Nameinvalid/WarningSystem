package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.IceGrade;
import com.Ice.WarningSystem.dao.IceGradeDao;
import com.Ice.WarningSystem.form.icegrade.DeleteIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.InsertIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.SelectIceGradeForm;
import com.Ice.WarningSystem.form.icegrade.UpdateIceGradeForm;
import com.Ice.WarningSystem.service.IceGradeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IceGradeServiceImpl implements IceGradeService {

    @Resource
    IceGradeDao iceGradeDao;

    @Override
    public List<IceGrade> selectIceGrade(SelectIceGradeForm selectIceGradeForm) {
        LambdaQueryWrapper<IceGrade> wrapper=new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(selectIceGradeForm.getIceGrade())){
            wrapper.like(IceGrade::getIceGrade,selectIceGradeForm.getIceGrade());
        }
        wrapper.orderByAsc(IceGrade::getId);
        return iceGradeDao.selectList(wrapper);
    }

    @Override
    public int insertIceGrade(InsertIceGradeForm insertIceGradeForm) {
        IceGrade iceGrade=new IceGrade();
        iceGrade.setSrcThickness(insertIceGradeForm.getSrcThickness());
        iceGrade.setDistThickness(insertIceGradeForm.getDistThickness());
        iceGrade.setGradeColor(insertIceGradeForm.getGradeColor());
        iceGrade.setIceGrade(insertIceGradeForm.getIceGrade());
        return iceGradeDao.insert(iceGrade);
    }

    @Override
    public int updateIceGrade(UpdateIceGradeForm updateIceGradeForm) {
        LambdaUpdateWrapper<IceGrade> wrapper=new LambdaUpdateWrapper<>();
        IceGrade iceGrade=new IceGrade();
        iceGrade.setSrcThickness(updateIceGradeForm.getSrcThickness());
        iceGrade.setDistThickness(updateIceGradeForm.getDistThickness());
        iceGrade.setIceGrade(updateIceGradeForm.getIceGrade());
        iceGrade.setGradeColor(updateIceGradeForm.getGradeColor());
        wrapper.eq(IceGrade::getId,updateIceGradeForm.getIceGradeId());
        return iceGradeDao.update(iceGrade,wrapper);
    }

    @Override
    public int deleteIceGrade(DeleteIceGradeForm deleteIceGradeForm) {
        LambdaQueryWrapper<IceGrade> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(IceGrade::getId,deleteIceGradeForm.getIceGradeId());
        return iceGradeDao.delete(wrapper);
    }
}
