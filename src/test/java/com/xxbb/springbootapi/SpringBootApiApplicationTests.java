package com.xxbb.springbootapi;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootTest
class SpringBootApiApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("123456"));

//        bCryptPasswordEncoder.encode("123456");

        System.out.println(bCryptPasswordEncoder.encode("123456"));
//        System.out.println(PBKDF2Util.getPasswrod("1"));

    }

}
