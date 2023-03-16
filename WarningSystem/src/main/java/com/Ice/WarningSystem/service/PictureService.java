package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.bean.Picture;
import com.Ice.WarningSystem.form.picture.InsertPicture;
import com.Ice.WarningSystem.form.picture.SelectPicturePage;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface PictureService {
    IPage<Picture> selectPicturePage(SelectPicturePage picturePage);

    boolean insertPicture(InsertPicture insertPicture);
}
