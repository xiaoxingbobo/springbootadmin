package com.xxbb.springbootapi.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import java.nio.charset.StandardCharsets;

public class AesUtil {
    //生成密钥
    static final byte[] KEY = "!@#$%^&123hsjFJS".getBytes(StandardCharsets.UTF_8);
    //构建
    static final AES AES_OBJ = SecureUtil.aes(KEY);

    /**
     * 加密
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        //加密
        byte[] encrypt = AES_OBJ.encrypt(content);
        //加密为16进制表示
        return AES_OBJ.encryptHex(content);
    }

    /**
     *
     * 解密
     * @param encryptHex
     * @return
     */
    public static String decrypt(String encryptHex) {
        //解密为原字符串
        return AES_OBJ.decryptStr(encryptHex);
    }
}
