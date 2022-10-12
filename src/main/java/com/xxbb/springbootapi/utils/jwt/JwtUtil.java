package com.xxbb.springbootapi.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xxbb.springbootapi.utils.AesUtil;

import java.util.Calendar;
import java.util.Objects;

public class JwtUtil {
    private static final String SING = "Sign:~!@#$%^&*()_+202102121744";

    /**
     * 创建token
     * （playload,过期时长/秒）
     *
     * @return
     */

    public static JWTCreator.Builder createBuilder() {
        //创建Builder
        return JWT.create();
    }

    private static String create(JWTCreator.Builder builder, int duration) {
        //创建过期时间戳
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, duration);
        if (Objects.isNull(builder)) {
            builder = createBuilder();
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
    public static String getToken(JWTCreator.Builder builder) {
        return create(builder, 60);
    }

    /***
     * 获取token
     * @return
     */
    public static String getToken(JWTCreator.Builder builder, int duration) {
        return create(builder, duration);
    }
}
