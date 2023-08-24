package com.zjc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 图片保存路径，自动从yml文件中获取数据
     * 示例： E:/images/
     */
    @Value("${movieimages-save-path}")
    private String ImagesSavePath;

    @Value("${movie-save-path}")
    private String MoviesSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 配置资源映射
         * 意思是：如果访问的资源路径是以“/images/”开头的，
         * 就给我映射到本机的“E:/images/”这个文件夹内，去找你要的资源
         * 注意：E:/images/ 后面的 “/”一定要带上
         */
        registry.addResourceHandler("/iqiyi/images/**")
                .addResourceLocations("file:" + ImagesSavePath);
        registry.addResourceHandler("/iqiyi/movies/**")
                .addResourceLocations("file:" + MoviesSavePath);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        factory.setMaxFileSize(DataSize.parse("1024000KB")); // KB,MB
        // 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse("10240000KB"));
        return factory.createMultipartConfig();
    }
}