package com.xxbb.springbootapi.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Administrator
 * 条件类
 */
@Data
public class Search {
    @ApiModelProperty(value = "字段名")
    private String field;
    @ApiModelProperty(value = "关键字")
    private String keyword;
}
