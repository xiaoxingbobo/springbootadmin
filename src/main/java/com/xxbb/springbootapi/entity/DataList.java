package com.xxbb.springbootapi.entity;

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
public class DataList<T> {
    @ApiModelProperty("数据条数")
    private Integer total;
    @ApiModelProperty("数据列表")
    private List<T> list;
}
