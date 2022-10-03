package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.JsonResult;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.entity.dto.LoginInput;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;

public interface IUserService extends IBaseService<User, UserQuery, UserUpdate, UserMapper> {
    User getSiteOperator();

    JsonResult login(LoginInput input);
}
