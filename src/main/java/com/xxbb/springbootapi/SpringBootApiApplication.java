package com.xxbb.springbootapi;


import com.xxbb.springbootapi.config.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @author Administrator
 */
@Slf4j
@SpringBootApplication
public class SpringBootApiApplication {
    public static void main(String[] args) throws Exception {
        log.info("Application running...");
        SpringApplication.run(SpringBootApiApplication.class, args);
    }

}
