package com.xxbb.springbootapi.interceptor;

import com.xxbb.springbootapi.entity.JsonResult;
import com.xxbb.springbootapi.entity.JsonResultData;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestControllerAdvice(basePackages = "com.xxbb.springbootapi.controller")

public class ResponseResultHandler implements ResponseBodyAdvice  {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }




    @Override
    @ResponseStatus(code = HttpStatus.RESET_CONTENT)
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Boolean) {
            if ((Boolean) o) {
                return new JsonResult().Success();
            }else {
                return new JsonResult().Fail();
            }
        }
        else if(o instanceof List){
            return new JsonResultData<>(o).Success();
        }
        else if(o instanceof JsonResultData){
            return o;
        }
        else if(o instanceof JsonResult){
            return o;
        }else if (o==null){
            return new JsonResult().Success("空空如也");
        }
        return new JsonResultData<>(o).Success();
    }
}
