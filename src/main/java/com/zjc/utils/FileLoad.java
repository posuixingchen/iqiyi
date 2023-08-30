package com.zjc.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileLoad {
    @Value("${movieimages-save-path}")
    private static String ImagesSavePath;

    public static String upload(@RequestParam(value = "file") MultipartFile file) {
        if (file != null) {
            File dir = new File(ImagesSavePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //给文件重新设置一个名字
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

            //创建新文件
            File newFile = new File(ImagesSavePath + newFileName);
            //复制操作
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String base64Str = null;
            try {
                base64Str = Base64.encodeBase64String(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return base64Str;
        } else {
            return "文件不能为空";
        }
    }
}
