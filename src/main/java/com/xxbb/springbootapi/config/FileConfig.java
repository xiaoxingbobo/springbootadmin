package com.xxbb.springbootapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Autowired
    private WebConfig webConfig;

    /**
     * 实现资源映射访问路径方法
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 资源映射路径
         * addResourceHandler：访问映射路径
         * addResourceLocations：资源绝对路径
         */
        // 获取对应的系统
//        String os = System.getProperty("os.name");
        // 如果是window系统
//        if (os.toLowerCase().startsWith("win")) {
//            registry.addResourceHandler("/fileData/**").addResourceLocations("file:" + windir);
//        } else {
//            registry.addResourceHandler("/fileData/**").addResourceLocations("file:" + lindir);
//        }

        registry.addResourceHandler(webConfig.getUploadPrefix()+"/**").addResourceLocations("file:" + webConfig.getUploadPath());

    }
}