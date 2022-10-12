package com.xxbb.springbootapi.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    //设置cookie
    public static HttpServletResponse setCookie(HttpServletResponse response, String key, String value){
        //把token放到cookie里
        Cookie cookie = new Cookie(key,value);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*12);//12小时
        response.addCookie(cookie);
        return response;
    }
    //清除cookie
    public static HttpServletResponse clearCookie(HttpServletResponse response, String key){
        //把token放到cookie里
        Cookie cookie = new Cookie(key,null);
        cookie.setPath("/");
        cookie.setMaxAge(0);//0小时,cookie则就清空
        response.addCookie(cookie);
        return response;
    }
    //获取cookie
    public static String getCookie(HttpServletRequest request,String key){
        String token=null;
        //获取token
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(key)){
                    token= cookie.getValue();
                }
            }
        }
        return  token;
    }
}
