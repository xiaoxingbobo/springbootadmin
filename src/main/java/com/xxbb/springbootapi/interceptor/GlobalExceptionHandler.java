package com.xxbb.springbootapi.interceptor;


import com.xxbb.springbootapi.entity.dto.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonResult exceptionHandler(Exception e) {
        //如果是合法异常
        if (e instanceof LegalException) {
            log.error("自定义异常：" + e.getMessage());
            return new JsonResult().Error(e.getMessage());
        } else if (e instanceof AccessDeniedException) {
            return new JsonResult().Unauthorized();
        } else if (e instanceof NoHandlerFoundException) {
            return new JsonResult().NotFound();
        }
        log.error("发生未知异常！信息：" + e + "，" + e.getMessage());
        if (e.toString().contains(e.getMessage())) {
            return new JsonResult().Unknown("你请求的对象发生了未知异常！并给你留了一句话：" + e.getMessage());
        }
        return new JsonResult().Unknown("你请求的对象发生了未知异常！并给你留了一句话：" + e + "，" + e.getMessage());
    }
}