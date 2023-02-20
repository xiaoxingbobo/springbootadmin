package com.xxbb.springbootapi;

import com.xxbb.springbootapi.service.impl.SysAuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootApiApplicationTests {

    @Autowired
    SysAuthorityService sysAuthorityService;
    @Test
    void contextLoads() {

    }


}
