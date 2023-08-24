package com.zjc.utils;

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

    public static String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
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
                //协议 :// ip地址 ：端口号 / 文件目录(/images/xxx.jpg)
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + newFileName;
                System.out.println("图片上传，访问URL：" + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newFile.getAbsolutePath();
        } else {
            return "文件不能为空";
        }
    }
}
