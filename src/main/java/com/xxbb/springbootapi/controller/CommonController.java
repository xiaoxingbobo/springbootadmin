package com.xxbb.springbootapi.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequestMapping("common")
@Api(tags = "公共接口")
@RestController
public class CommonController {

    @GetMapping("captcha")
    @ApiOperation(value = "验证验证码")
    @ResponseBody
    public void captcha(String server, String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        GifCaptcha captcha = CaptchaUtil.createGifCaptcha(150, 40, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write(response.getOutputStream());
        //获取验证码中的文字内容
        String verifyCode = captcha.getCode();
        request.getSession().setAttribute("verifyCode", verifyCode);
    }
}
