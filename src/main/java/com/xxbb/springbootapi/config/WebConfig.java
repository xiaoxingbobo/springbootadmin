package com.xxbb.springbootapi.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
@Data//get set
@Configuration
public class WebConfig {
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.prefix}")
    private String uploadPrefix;
}
