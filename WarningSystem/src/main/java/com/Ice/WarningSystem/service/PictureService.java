package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.bean.Picture;
import com.Ice.WarningSystem.form.picture.InsertPicture;
import com.Ice.WarningSystem.form.picture.SelectPicturePage;
import com.Ice.WarningSystem.vo.PictureVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface PictureService {
    IPage<PictureVO> selectPicturePage(SelectPicturePage picturePage);

    String selectUpdatePictureUser(Long updateUserId);

    boolean insertPicture(InsertPicture insertPicture);
}
