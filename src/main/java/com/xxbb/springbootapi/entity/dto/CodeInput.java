package com.xxbb.springbootapi.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)//链式调用
public class CodeInput {
    @ApiModelProperty("实体名称")
    private String entityName;
    @ApiModelProperty("实体字段")
    private List<EntityField> entityFields;
    @ApiModelProperty("是否覆盖")
    private  Boolean isCover=false;
}
