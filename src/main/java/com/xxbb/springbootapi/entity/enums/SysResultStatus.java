package com.xxbb.springbootapi.entity.enums;

public enum SysResultStatus {
    SUCCESS(true, "成功"),
    ERROR(false, "失败");

    private Boolean code;
    private String message;
    SysResultStatus(Boolean code, String message) {
        this.code = code;
        this.message = message;
    }
    public Boolean getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
