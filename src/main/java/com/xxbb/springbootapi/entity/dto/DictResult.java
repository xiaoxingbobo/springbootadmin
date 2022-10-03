package com.xxbb.springbootapi.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)//链式调用
public class DictResult {
    private int value;
    private String label;
}
