package com.xxbb.springbootapi.interceptor;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xxbb.springbootapi.util.IpUtil;
import com.xxbb.springbootapi.util.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=null;
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
        if (token != null) {
            try {
                JwtUtil.verifyToken(token);
                //判断是否哟权限浏览页面
                if (verifyAuthority(request, token)) {
                    return true;
                }
                //否则
                throw new LegalException("您没有权限！");
            } catch (SignatureVerificationException exception) {
                throw new LegalException("无效签名！");
            } catch (TokenExpiredException exception) {
                throw new LegalException("token过期！");

            } catch (AlgorithmMismatchException exception) {
                //记录ip
                log.info("违规操作：" + IpUtil.getIp(request));
                throw new LegalException("token算法不一致！请勿违规操作，否则后果自负！");

            } catch (InvalidClaimException exception) {

                throw new LegalException("token无效！");
            }
        } else {
            throw new LegalException("未提交token，请在请求头部（header）或Cookie中添加Authorization参数");
        }
    }

    private Boolean verifyAuthority(HttpServletRequest request, String token) {
        String requestUrl = request.getRequestURI();
        DecodedJWT jwt = JwtUtil.verifyToken(token);
        String auth = jwt.getClaim("authority").asString();
        //判断请求路径是否包含在请求权限中
        return auth.contains(requestUrl) || auth.contains("/all");
    }

}
