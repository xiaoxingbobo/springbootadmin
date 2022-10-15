package com.xxbb.springbootapi.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CodeInput {
    @ApiModelProperty("实体名称")
    private String entityName;
    @ApiModelProperty("是否覆盖")
    private  Boolean isCover=false;
}
