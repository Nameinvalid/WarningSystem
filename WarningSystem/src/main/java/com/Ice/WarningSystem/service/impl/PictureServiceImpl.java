package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.Picture;
import com.Ice.WarningSystem.dao.PictureDao;
import com.Ice.WarningSystem.form.picture.DeletePicture;
import com.Ice.WarningSystem.form.picture.InsertPicture;
import com.Ice.WarningSystem.form.picture.SelectPicturePage;
import com.Ice.WarningSystem.form.picture.UpdatePicture;
import com.Ice.WarningSystem.mapper.UserMapper;
import com.Ice.WarningSystem.service.PictureService;
import com.Ice.WarningSystem.service.UploadService;
import com.Ice.WarningSystem.util.ConversionUtil;
import com.Ice.WarningSystem.vo.PictureVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class PictureServiceImpl implements PictureService {
    @Resource
    PictureDao pictureDao;

    @Autowired
    UploadService uploadService;

    @Resource
    UserMapper userMapper;

    @Override
    public IPage<PictureVO> selectPicturePage(SelectPicturePage picturePage) {
        Page<Picture> page=new Page<>(picturePage.getPageNum(), picturePage.getPageSize());
        LambdaUpdateWrapper<Picture> wrapper=new LambdaUpdateWrapper<>();
        if (StringUtils.isNotBlank(picturePage.getPhotoName())){
            wrapper.like(Picture::getPhotoName,picturePage.getPhotoName());
        }
        wrapper.orderByAsc(Picture::getCreateTime);
        page=pictureDao.selectPage(page,wrapper);
        IPage<PictureVO> pictureVOIPage= ConversionUtil.IpageTypeConversion(page,PictureVO.class);
        pictureVOIPage.getRecords().forEach(pictureVO -> {
            pictureVO.setUpdateUserName(selectUpdatePictureUser(pictureVO.getUpdateUserId()));
        });
        return pictureVOIPage;
    }

    /**
     * 寻找修改图片人的名称/网络名称
     * @param updateUserId
     * @return
     */
    @Override
    public String selectUpdatePictureUser(Long updateUserId) {
        return userMapper.findUsername(updateUserId);
    }

    @Override
    public boolean insertPicture(InsertPicture insertPicture) {
        Picture picture= new Picture();
        picture.setPhotoName(insertPicture.getPhotoName());
        picture.setRemark(insertPicture.getRemark());
        picture.setPosition(insertPicture.getPosition());
        picture.setCreateTime(new Date());
        picture.setUpdateTime(new Date());
        picture.setPhotoUrl(insertPicture.getUrl());
        //url需要调用uploadService中的上传

        int count=pictureDao.insert(picture);
        Long pictureId=picture.getId();
        if (pictureId==null|| count==0){
            return false;
        }else {
            LambdaUpdateWrapper<Picture> wrapper=new LambdaUpdateWrapper<>();
            wrapper.eq(Picture::getId,pictureId).set(Picture::getCreateUserId,insertPicture.getUpdateUserId())
                    .set(Picture::getUpdateUserId,insertPicture.getUpdateUserId());
            int countId=pictureDao.update(null,wrapper);
            return countId==1;
        }
    }

    @Override
    public boolean updatePicture(UpdatePicture updatePicture) {
        Picture picture=new Picture();
        picture.setPhotoName(updatePicture.getPhotoName());
        picture.setRemark(updatePicture.getRemark());
        picture.setPosition(updatePicture.getPosition());
        picture.setPhotoUrl(updatePicture.getUrl());
        picture.setUpdateTime(new Date());
        picture.setUpdateUserId(updatePicture.getUpdateUserId());
        LambdaUpdateWrapper<Picture> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Picture::getId,updatePicture.getPhotoId());
        int count=pictureDao.update(picture,wrapper);
        return count==1;
    }

    @Override
    public boolean deletePicture(DeletePicture deletePicture) {
        int count=pictureDao.deleteById(deletePicture.getPhotoId());
        return count==1;
    }
}
