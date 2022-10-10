package com.xxbb.springbootapi.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)//链式调用
public class PagedInput{
    @ApiModelProperty(value = "当前页")
    private Integer current=1;
    @ApiModelProperty(value = "每页条数")
    private Integer size=10;
}
