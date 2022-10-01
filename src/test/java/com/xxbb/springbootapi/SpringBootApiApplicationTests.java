package com.xxbb.springbootapi;

import com.xxbb.springbootapi.util.Pbkdf2Util;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class SpringBootApiApplicationTests {

    @Test
    void contextLoads() {
//        System.out.println(PBKDF2Util.getPasswrod("1"));
        System.out.println(Pbkdf2Util.verify("2296285443c569971a7c986edbbd45ce82b8cddd9c644173a54e7e5322b23875","2"));
    }

}
