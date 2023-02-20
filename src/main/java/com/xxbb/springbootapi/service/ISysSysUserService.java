package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.SysUser;
import com.xxbb.springbootapi.entity.dto.JsonResult;
import com.xxbb.springbootapi.entity.dto.LoginInput;
import com.xxbb.springbootapi.entity.dto.LoginResult;
import com.xxbb.springbootapi.mapper.SysUserMapper;
import com.xxbb.springbootapi.wrapper.SysUserQuery;
import com.xxbb.springbootapi.wrapper.SysUserUpdate;

public interface ISysSysUserService extends ISysBaseService<SysUser, SysUserQuery, SysUserUpdate, SysUserMapper> {

    JsonResult login(LoginInput input);

    JsonResult logout();

    LoginResult getCurrentUser();

    boolean add(SysUser sysUser);

}