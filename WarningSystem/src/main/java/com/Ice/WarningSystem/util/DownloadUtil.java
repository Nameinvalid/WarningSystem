package com.Ice.WarningSystem.util;

import com.Ice.WarningSystem.Exception.UploadSizeExceededException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;

@Slf4j
public class DownloadUtil {

    public static void fileSizeCheck(long fileSize, long maxFileSize) throws UploadSizeExceededException {
        log.info("maxFileSize：" + maxFileSize);
        if (maxFileSize < fileSize) {
            long g = maxFileSize / (1024 * 1024 * 1024);
            long m = maxFileSize / (1024 * 1024) - g;
            String msg = "文件大小不可超过" + (g == 0 ? "" : g + "GB,") + (m == 0 ? "" : m + "MB!");
            throw new UploadSizeExceededException(msg);
        }
    }


    public static void writeStream(String filePath, String fileContentType, HttpServletResponse response) {

        response.setContentType(fileContentType);
        // URLEncoder.encode(fileNameString, "UTF-8") 下载文件名为中文的，文件名需要经过url编码
        ServletOutputStream out = null;
        InputStream in = null;
        try {

            String fileName;
            if(filePath.lastIndexOf("/")==-1){
                fileName=filePath.substring(filePath.lastIndexOf("\\") + 1);
            }else{
                fileName=filePath.substring(filePath.lastIndexOf("/") + 1);
            }
            HashMap<String,String> contentType=new HashMap<>();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            File file;
            in = new FileInputStream(filePath);
            out = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int count;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                assert in != null;
                in.close();
                assert out != null;
                out.flush();
                out.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public static String getContentType(String filepath){

        String suffix = filepath.substring(filepath.lastIndexOf(".") + 1);
        HashMap<String,String> contentType=new HashMap<>();
        contentType.put("tif","image/tiff");
        contentType.put("dbf","application/x-dbf");
        if(contentType.containsKey(suffix)){
            return contentType.get(suffix);
        }
        return "application/octet-stream";

    }

}
