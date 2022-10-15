package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.entity.dto.JsonResult;
import com.xxbb.springbootapi.entity.dto.LoginInput;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;

public interface IUserService extends IBaseService<User, UserQuery, UserUpdate, UserMapper> {

    User getUserInfo();

    JsonResult login(LoginInput input);

    JsonResult logout();

    User getUser();

    boolean add(User user);

}