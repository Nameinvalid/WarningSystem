package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.service.UploadService;
import com.Ice.WarningSystem.util.FileUtil;
import com.Ice.WarningSystem.util.QiniuUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    QiniuUtils qiniuUtils;

    @Override
    @Transactional
    public String uploadPicture(MultipartFile file) {
        if (file.isEmpty()){
            return null;
        }
        String pictureName= file.getOriginalFilename();
        try {
            InputStream uploadFile= file.getInputStream();
            String picturePath=qiniuUtils.upload(uploadFile,pictureName);
            picturePath= "http://" +picturePath;
            return picturePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
