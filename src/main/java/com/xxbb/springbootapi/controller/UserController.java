package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.service.impl.UserService;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("Users")
@Api(tags = "User表")
@RestController
public class UserController extends ApiController<User, UserQuery, UserUpdate, UserMapper> {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取当前登录者信息")
    public User getUserInfo() {

        return userService.getSiteOperator();
    }
}
