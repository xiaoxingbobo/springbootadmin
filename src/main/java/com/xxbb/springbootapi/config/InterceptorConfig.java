
package com.xxbb.springbootapi.config;

import com.xxbb.springbootapi.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private WebConfig webConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放行路径
        List<String> excludePathPatternsList = new ArrayList<>();
        excludePathPatternsList.add("/Accounts/login");
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
        excludePathPatternsList.add(webConfig.getUploadPrefix() + "/**");//静态资源文件
        //eoorer page
        excludePathPatternsList.add("/404.html");
        excludePathPatternsList.add("/500.html");
        //拦截路径
        List<String> addPathPatternsList = new ArrayList<>();
        addPathPatternsList.add("/**");
        //注册
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns(addPathPatternsList)
                .excludePathPatterns(excludePathPatternsList);
    }
}
