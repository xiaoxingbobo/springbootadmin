package com.xxbb.springbootapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Autowired
    private AppConfig appConfig;

    /**
     * 跨域配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//允许跨域的路径
                .allowedOriginPatterns("*")//允许跨域的域名
                .allowCredentials(true)//允许cookie
                .allowedMethods("GET", "POST", "DELETE", "PUT")//允许请求方式
                .allowedHeaders("*")//允许请求头
                .maxAge(3600);//允许跨域请求事件
    }

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
        excludePathPatternsList.add("/user/login");
        excludePathPatternsList.add("/user/logout");
        excludePathPatternsList.add("/common/captcha");
        //knife4j文档
        excludePathPatternsList.add("/doc.html");
        //swagger
        excludePathPatternsList.add("/swagger-resources/**");
        excludePathPatternsList.add("/webjars/**");
        excludePathPatternsList.add("/v2/**");
        excludePathPatternsList.add("/favicon.ico");
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
//        registry.addInterceptor(new JWTInterceptor())//添加拦截器获取token
//                .addPathPatterns(addPathPatternsList)
//                .excludePathPatterns(excludePathPatternsList);
    }
}
