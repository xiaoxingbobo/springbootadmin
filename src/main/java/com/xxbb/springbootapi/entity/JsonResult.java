package com.xxbb.springbootapi.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
@JsonSerialize
public class JsonResult implements Serializable {
    private Boolean state;
    private String message;
    private Integer code;

    public JsonResult(Integer code, String msg, Boolean state) {
        this.code = code;
        this.message = msg;
        this.state = state;
    }
    /*
     * 成功
     * */
    public JsonResult Success() {
        this.state = true;
        this.message = "成功";
        this.code = 1;
        return this;
    }
    public JsonResult Success(String message) {
        this.code = 1;
        this.message = message;
        this.state = true;
        return this;
    }
    /**
     * 失败
     */
    public JsonResult Fail() {
        this.state = false;
        this.message = "失败";
        this.code = 0;
        return this;
    }
    /**
     * 失败
     */
    public JsonResult Fail(String message) {
        this.state = false;
        this.message = message;
        this.code = 0;
        return this;
    }

    /**
     * 异常
     */
    public JsonResult Error() {
        this.state = false;
        this.message = "异常";
        this.code = -1;
        return this;
    }

    /**
     * 异常
     */
    public JsonResult Error(String message) {
        this.state = false;
        this.message = message;
        this.code = -1;
        return this;
    }

    /**
     * 未知
     */
    public JsonResult Unknown() {
        this.state = false;
        this.message = "未知";
        this.code = -2;
        return this;
    }
    public JsonResult Unknown(String message) {
        this.state = false;
        this.message = message;
        this.code = -2;
        return this;
    }
}
