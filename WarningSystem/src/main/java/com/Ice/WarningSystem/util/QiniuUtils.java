package com.Ice.WarningSystem.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class QiniuUtils {
    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.bucketName}")
    private String bucketName;
    @Value("${qiniu.path}")
    private String url;

    public String upload(InputStream file, String fileName) throws QiniuException {
        String token = auth.uploadToken(bucketName);
        Response res = uploadManager.put(file, fileName, token, null, null);
        if (!res.isOK()) {
            throw new RuntimeException("上传七牛云出错:" + res);
        }
        DefaultPutRet putRet=new Gson().fromJson(res.bodyString(),DefaultPutRet.class);
        return url + "/" + putRet.key;
    }
}
