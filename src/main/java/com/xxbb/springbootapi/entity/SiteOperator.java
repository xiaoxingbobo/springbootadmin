package com.xxbb.springbootapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
public class SiteOperator {
    private Integer id;
    private Integer userId;
    private String name;
    private String token;
    private String authority;
}
