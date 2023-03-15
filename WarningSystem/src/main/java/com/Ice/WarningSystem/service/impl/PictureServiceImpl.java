package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.Picture;
import com.Ice.WarningSystem.dao.PictureDao;
import com.Ice.WarningSystem.form.picture.SelectPicturePage;
import com.Ice.WarningSystem.service.PictureService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PictureServiceImpl implements PictureService {
    @Resource
    PictureDao pictureDao;

    @Override
    public IPage<Picture> selectPicturePage(SelectPicturePage picturePage) {
        Page<Picture> page=new Page<>(picturePage.getPageNum(), picturePage.getPageSize());
        LambdaUpdateWrapper<Picture> wrapper=new LambdaUpdateWrapper<>();
        if (StringUtils.isNotBlank(picturePage.getPhotoName())){
            wrapper.like(Picture::getPhotoName,picturePage.getPhotoName());
        }
        wrapper.orderByAsc(Picture::getCreateTime);
        page=pictureDao.selectPage(page,wrapper);
        return page;
    }
}
