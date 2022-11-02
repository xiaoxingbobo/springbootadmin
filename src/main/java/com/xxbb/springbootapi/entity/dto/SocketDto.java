package com.xxbb.springbootapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class SocketDto {
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("信息")
    private String message;
    @ApiModelProperty("创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
