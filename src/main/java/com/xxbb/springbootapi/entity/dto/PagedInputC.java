package com.xxbb.springbootapi.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PagedInputC<T> extends PagedInput {
    @ApiModelProperty(value = "实体类")
    private T condition;
}