package com.Ice.WarningSystem.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadPicture(MultipartFile file);
}
