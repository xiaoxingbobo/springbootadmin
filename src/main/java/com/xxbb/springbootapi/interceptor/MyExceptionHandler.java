package com.xxbb.springbootapi.interceptor;


import com.xxbb.springbootapi.entity.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
public class MyExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonResult exceptionHandler(Exception e) {
        //如果是合法异常
        if (e instanceof LegalException){
            log.error("自定义异常："+e.getMessage());
            return new JsonResult().Error(e.getMessage());
        }
        log.error("发生异常！信息："+e+"："+e.getMessage());
        if (e.toString().contains(e.getMessage()))
            return new JsonResult().Unknown("发生了未知异常！信息："+e.getMessage());
        return new JsonResult().Unknown("发生了未知异常！信息："+e+"："+e.getMessage());
    }
}