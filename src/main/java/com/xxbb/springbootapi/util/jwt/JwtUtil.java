package com.xxbb.springbootapi.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xxbb.springbootapi.util.AesUtil;


import java.util.*;

public class JwtUtil {
    private static final String SING = "Sign:~!@#$%^&*()_+202102121744";

    /**
     * 创建token
     * （playload,过期时长/秒）
     */

    private static String create(Map<String, String> map, int duration) {
        //创建过期时间戳
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, duration);

        //创建Builder
        final JWTCreator.Builder builder = JWT.create();
        //playload
        if (map != null) {
            map.forEach(builder::withClaim);
        }
        //生成token
        String token = builder.withExpiresAt(instance.getTime())//过期时间
                .sign(Algorithm.HMAC256(SING));//签名
        //Aes加密
        return AesUtil.encrypt(token);
    }

    /**
     * 验证token
     */
    public static DecodedJWT verifyToken(String _token) {
        //Aes解密
        String token = AesUtil.decrypt(_token);
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);

    }

    /***
     * 获取token
     * @return
     */
    public static String getToken() {
        return create(null, 60);
    }

    /***
     * 获取token
     * @return
     */
    public static String getToken(Map<String, String> map) {
        return create(map, 60);
    }

    /***
     * 获取token
     * @return
     */
    public static String getToken(Map<String, String> map, int duration) {
        return create(map, duration);
    }
}
