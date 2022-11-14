package com.xxbb.springbootapi.entity.dto.enums;


public enum AuthorityType {
    ROUTER("Router"), API("API");
    private final String code;

    AuthorityType(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}
