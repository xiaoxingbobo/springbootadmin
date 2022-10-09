package com.xxbb.springbootapi.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@AllArgsConstructor
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
@Data
@JsonSerialize
public class JsonResultData<T> extends JsonResult implements Serializable {
    @ApiModelProperty("数据")
    private T data;
    //构造函数

    public JsonResultData(Integer code, String msg, Boolean state, T data){
        super(code,msg,state);
        this.data = data;
    }

    /**
     * 成功
     * @return
     */
    public JsonResultData<T> Success() {
        this.setCode(1);
        this.setMessage("成功");
        this.setState(true);
        return this;
    }
    public JsonResultData<T> Success(T data) {
        this.setCode(1);
        this.setMessage("成功");
        this.setState(true);
        this.setData(data);
        return this;
    }
    public JsonResultData<T> Success(T data,String message) {
        this.setCode(1);
        this.setMessage(message);
        this.setState(true);
        this.setData(data);
        return this;
    }

    /**
     * 未知
     * @param message
     * @return
     */
    public JsonResultData<T> Unknow(String message){
        super.Unknown(message);
        return this;
    }

    /**
     * 异常
     * @param message
     * @return
     */
    public JsonResultData<T> Error(String message){
        super.Error(message);
        return this;
    }
}
