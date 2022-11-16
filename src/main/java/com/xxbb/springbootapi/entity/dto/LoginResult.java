package com.xxbb.springbootapi.entity.dto;

import com.xxbb.springbootapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
public class LoginResult {
    private String token;
    private String routePath;
    private User userInfo;
    private List<RoleAuthorityResult> roleAuthority;
    private boolean captcha;
    private List<String> authorityValues;


}
