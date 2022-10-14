package com.xxbb.springbootapi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@Slf4j
@SpringBootApplication
@EnableAsync//开启异步
public class SpringBootApiApplication {
    public static void main(String[] args) throws Exception {
        log.info("Application running...");
        SpringApplication.run(SpringBootApiApplication.class, args);
    }

}
