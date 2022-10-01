package com.xxbb.springbootapi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class SpringBootApiApplication {
    public static void main(String[] args) throws Exception {
        log.info("Application running...");
        SpringApplication.run(SpringBootApiApplication.class, args);
    }

}
