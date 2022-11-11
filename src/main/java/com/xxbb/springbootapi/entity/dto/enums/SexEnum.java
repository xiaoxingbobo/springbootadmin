package com.xxbb.springbootapi.entity.dto.enums;

/**
 * @author xaioxingbobo
 * @date 2022/11/3 10:01
 */
public enum SexEnum {
    Man("男"), Woman("女");

    private final String name;

    SexEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
