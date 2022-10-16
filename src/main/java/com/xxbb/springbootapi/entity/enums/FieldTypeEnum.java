package com.xxbb.springbootapi.entity.enums;

//java基本包装类型
public enum FieldTypeEnum {
    STRING("String"),
    INTEGER("Integer"),
    LONG("Long"),
    DOUBLE("Double"),
    FLOAT("Float"),
    BOOLEAN("Boolean"),
    DATETIME("Date"),
    BIGDECIMAL("BigDecimal");
    private String code;

    FieldTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
