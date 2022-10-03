package com.xxbb.springbootapi.entity.dto;

import lombok.Data;

@Data
public class UserInfo {
    private String username;
    private String captcha;
    private String name;
}
