package com.xxbb.springbootapi.entity.dto;

import com.xxbb.springbootapi.entity.Authority;
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
public class MenuResult extends Authority {
    @ApiModelProperty("子组件")
    private List<MenuResult> children;
    @ApiModelProperty("元信息")
    private Meta meta;
}
