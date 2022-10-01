package com.xxbb.springbootapi.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.xxbb.springbootapi.entity.SiteOperator;
import com.xxbb.springbootapi.service.ISiteOperatorService;
import com.xxbb.springbootapi.util.jwt.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@Service
public class SiteOperatorService implements ISiteOperatorService {

    public SiteOperator getOperator() {
        String token=null;
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        //从cookie获取token
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            //从cookie中获取
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    token = cookie.getValue();
                }
            }
        }
        //从请求头获取token
        if (token == null) {
            //从请求头中获取
            token = request.getHeader("Authorization");
        }
        //获取基本信息
        DecodedJWT jwt = JwtUtil.verifyToken(token);
        return new SiteOperator().setToken(token).setName(jwt.getClaim("name").asString()).setId(Integer.parseInt(jwt.getClaim("id").asString())).setUserId(Integer.parseInt(jwt.getClaim("userId").asString())).setAuthority(jwt.getClaim("account").asString());
    }
}
