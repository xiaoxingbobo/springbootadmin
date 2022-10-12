package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.entity.dto.JsonResult;
import com.xxbb.springbootapi.entity.dto.LoginInput;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.service.impl.UserService;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@Api(tags = "User表")
@RestController
public class UserController extends ApiController<User, UserQuery, UserUpdate, UserMapper> {
    @Autowired
    private UserService userService;

    @GetMapping("getUserInfo")
    @ApiOperation(value = "获取当前用户信息")
    public User getUser() {
        return userService.getUserInfo().setPassword(null);
    }


    @PostMapping("login")
    @ApiOperation(value = "登录")
    public JsonResult login(@RequestBody LoginInput input) {
        return userService.login(input);
    }

    @GetMapping("logout")
    @ApiOperation(value = "注销")
    public JsonResult logout() {
        return new JsonResult().Success();
    }
}
