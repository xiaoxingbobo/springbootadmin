package com.xxbb.springbootapi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Administrator
 */
@Slf4j
@SpringBootApplication
public class XiaoXingApplication {
    public static void main(String[] args) throws Exception {
        log.info("Application running...");
        SpringApplication.run(XiaoXingApplication.class, args);
    }

}
