package com.xxbb.springbootapi;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
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
    @Test
    public void generate() throws Exception {
        FileGenerator.build(Empty.class);
    }
    public static final String url = "jdbc:mysql://localhost:3306/springbootadmin?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";

    @Tables(
            // 设置数据库连接信息
            url = url, username = "root", password = "root",
            // 设置entity类生成src目录, 相对于 user.dir
            srcDir = "src/main/java/com/xxbb/springbootapi/fmentity",
            // 设置entity类的package值
            basePack = "cn.org.atool.fluent.mybatis.demo3",
            // 设置dao接口和实现的src目录, 相对于 user.dir
            daoDir = "src/main/java/com/xxbb/springbootapi/fmdao",
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = {"Role"})}
    )
    static class Empty {
    }


}
