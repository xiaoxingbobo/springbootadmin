package com.xxbb.springbootapi.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable
{
    @ApiModelProperty("响应状态：操作成功返回true，异常返回false")
    private Boolean status;
    @ApiModelProperty("响应信息")
    private String message;
    @ApiModelProperty("响应码：1成功，0异常，2未登录")
    private Integer code;
}
