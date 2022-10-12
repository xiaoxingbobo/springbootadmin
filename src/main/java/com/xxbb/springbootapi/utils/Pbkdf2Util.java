package com.xxbb.springbootapi.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Component;


/***
 * 生成随机盐，通过盐再进行加密
 * 该方式依赖hutool
 */

@Component
public class Pbkdf2Util {
    public static String encryption(String content) {
        String s = getSalt();
        byte[] salt = s.getBytes();
        return SecureUtil.md5(pbkdf2(content, salt)) + s;
    }

    private static String pbkdf2(String content, byte[] salt) {
        char[] chars = content.toCharArray();
        return SecureUtil.pbkdf2(chars, salt);
    }

    /**
     * 获取盐
     *
     * @return
     */
    private static String getSalt() {
        return IdUtil.simpleUUID();
    }

    /**
     * 验证密码
     *
     * @param password 密码
     * @return
     */
    public static Boolean verify(String password,String text) {
        //获取随机盐，从第32位开始是随机盐
        String s = password.substring(32);
        String pwd = SecureUtil.md5(pbkdf2(text, s.getBytes())) + s;
        return pwd.equals(password);
    }
}
