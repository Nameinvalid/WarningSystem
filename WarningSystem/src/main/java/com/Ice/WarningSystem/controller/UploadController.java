package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.service.UploadService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "UploadController",tags = "照片管理控制层")
@RestController
@Slf4j
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @PostMapping("/picture")
    @ApiOperation(value = "照片的上传")
    @CrossOrigin
    public HttpResult uploadPicture(@RequestParam("file") MultipartFile file){
        String pictureUrl=uploadService.uploadPicture(file);
        if (StringUtils.isBlank(pictureUrl)){
            return HttpResult.error("上传失败");
        }
        return HttpResult.ok("上传成功",pictureUrl);
    }
}
