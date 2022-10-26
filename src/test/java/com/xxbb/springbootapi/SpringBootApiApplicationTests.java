package com.xxbb.springbootapi;

import com.xxbb.springbootapi.service.impl.AuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootApiApplicationTests {

    @Autowired
    AuthorityService authorityService;
    @Test
    void contextLoads() {

    }


}
