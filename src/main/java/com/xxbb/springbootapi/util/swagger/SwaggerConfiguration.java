//package com.xxbb.springbootapi.util.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//
//
//这里只做事例，并没有使用
//@Configuration
//@EnableSwagger2
//public class Config {
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .pathMapping("/")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.xxbb.springbootapi"))
//                .paths(PathSelectors.any())
//                .build().apiInfo(new ApiInfoBuilder()
//                        .title("SpringBootApi")
//                        .description("一个快速开发框架，集成Fluent Mybatis、JWT、")
//                        .version("1.0")
//                        .contact(new Contact("小星博博","blog.csdn.net","xiaoxingbobo@Foxmail.com"))
//                        .license("官方网站")
//                        .licenseUrl("http://www.xiaoxingbobo.top")
//                        .build());
//    }
//}
