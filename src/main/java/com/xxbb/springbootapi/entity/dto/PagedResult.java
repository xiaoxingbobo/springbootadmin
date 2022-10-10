package com.xxbb.springbootapi.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
public class PagedResult<T> {
    @ApiModelProperty(value = "总条数")
    private Integer total;
    @ApiModelProperty(value = "总页数")
    private Integer pages;
    @ApiModelProperty(value = "当前页")
    private Integer current;
    @ApiModelProperty(value = "每页条数")
    private Integer size;
    @ApiModelProperty(value = "数据")
    private List<T> data;
}
