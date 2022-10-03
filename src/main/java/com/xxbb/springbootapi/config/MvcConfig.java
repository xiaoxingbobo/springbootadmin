package com.xxbb.springbootapi.config;

import com.xxbb.springbootapi.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private AppConfig appConfig;

    //配置接口前缀
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer
//                .addPathPrefix("/api", c -> c.isAnnotationPresent(Controller.class))
//                .addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放行路径
        List<String> excludePathPatternsList = new ArrayList<>();
        //默认接口
        excludePathPatternsList.add("/index/login");
        excludePathPatternsList.add("/common/captcha");
        //knife4j文档
        excludePathPatternsList.add("/doc.html");
        //swagger
        excludePathPatternsList.add("/swagger-resources/**");
        excludePathPatternsList.add("/webjars/**");
        excludePathPatternsList.add("/v2/**");
        excludePathPatternsList.add("/swagger-ui.html/**");
        //static
        excludePathPatternsList.add("/ws.html");
        excludePathPatternsList.add("/js/**");
        excludePathPatternsList.add("/css/**");
        excludePathPatternsList.add("/file/**");
        excludePathPatternsList.add(appConfig.getUploadPrefix() + "/**");//静态资源文件
        //eoorer page
        excludePathPatternsList.add("/404.html");
        excludePathPatternsList.add("/500.html");
        //拦截路径
        List<String> addPathPatternsList = new ArrayList<>();
        addPathPatternsList.add("/**");
        //注册
        registry.addInterceptor(new JWTInterceptor())//添加拦截器获取token
                .addPathPatterns(addPathPatternsList)
                .excludePathPatterns(excludePathPatternsList);
    }
}
