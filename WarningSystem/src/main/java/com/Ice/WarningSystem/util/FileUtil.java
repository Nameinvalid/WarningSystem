package com.Ice.WarningSystem.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
@Slf4j
public class FileUtil {
    /**
     * 读取文件内容为string
     * @param fileName
     * @return
     */
    public static String readFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            if(!jsonFile.exists()){
                return null;
            }try (FileReader fileReader = new FileReader(jsonFile);
                  Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");){
                int ch = 0;
                StringBuffer sb = new StringBuffer();
                while ((ch = reader.read()) != -1) {
                    sb.append((char) ch);
                }
                fileReader.close();
                reader.close();
                jsonStr = sb.toString();
                return jsonStr;
            }
        } catch (IOException e) {
            log.error("系统异常",e);
            return null;
        }
    }

    /**
     * 将MultipartFile转化为file并保存到服务器上的某地
     */
    public static void saveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException
    {
        try (FileOutputStream fs=new FileOutputStream(path +  "/" + savefile);){
            byte[] buffer =new byte[1024*1024];
            int bytesum = 0;
            int byteread = 0;
            while ((byteread=stream.read(buffer))!=-1)
            {
                bytesum+=byteread;
                fs.write(buffer,0,byteread);
                fs.flush();
            }
        }finally {
            stream.close();
        }

    }

}
