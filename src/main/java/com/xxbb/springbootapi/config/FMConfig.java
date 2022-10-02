package com.xxbb.springbootapi.config;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FMConfig {
    //FM配置
    @Bean
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}
