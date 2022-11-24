package com.xxbb.springbootapi.entity.dto.enums;

/**
 * @author Administrator
 */

//java基本包装类型
public enum FieldTypeEnum {
    STRING("String", "string"),
    INTEGER("Integer", "number"),
    LONG("Long", "number"),
    DOUBLE("Double", "number"),
    FLOAT("Float", "number"),
    SHORT("Short", "number"),
    BOOLEAN("Boolean", "boolean"),
    DATETIME("Date", "date"),
    BIGDECIMAL("BigDecimal", "number");
    private final String code;
    private final String type;
    FieldTypeEnum(String code, String type) {
        this.code = code;
        this.type = type;
    }
    public String getCode() {
        return code;
    }
    public String getType() {
        return type;
    }
}
