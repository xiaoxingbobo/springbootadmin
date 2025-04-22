package com.xxbb.springbootapi.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PagedInputT<T> extends PagedInput {
    @ApiModelProperty(value = "搜索字段")
    private List<Search> searches;
    @ApiModelProperty(value = "实体类")
    private T condition;
}