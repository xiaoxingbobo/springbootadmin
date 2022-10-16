package com.xxbb.springbootapi.entity.dto;

import com.xxbb.springbootapi.entity.enums.FieldTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)//链式调用
public class EntityField {
    @ApiModelProperty("字段名称")
    private String filedName;
    @ApiModelProperty("字段类型")
    private FieldTypeEnum fieldType;
    @ApiModelProperty("字段描述")
    private String description;
}
