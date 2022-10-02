package com.xxbb.springbootapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //配置接口前缀
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.addPathPrefix("/api", c -> c.isAnnotationPresent(Controller.class)).addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
    }
}
