package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.entity.dto.*;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.service.impl.UserService;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@Api(tags = "User表")
@RestController
public class UserController extends AuthApiController<User, UserQuery, UserUpdate, UserMapper> {
    @Autowired
    private UserService userService;
    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:add')")
    public Boolean create(User entity) {
        return super.create(entity);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:delete')")
    public Boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:delte')")
    public Boolean delete(List<Integer> ids) {
        return super.delete(ids);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:update')")
    public Boolean update(User entity) {
        return super.update(entity);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:select')")
    public List<User> select(List<Search> searches) {
        return super.select(searches);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:select')")
    public User find(int id) {
        return super.find(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:select')")
    public List<User> select(int limit) {
        return super.select(limit);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:select')")
    public List<User> select() {
        return super.select();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:select')")
    public PagedResult<User> select(PagedInputC<User> input) {
        return super.select(input);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:select')")
    public PagedResult<User> paged(int current, int size) {
        return super.paged(current, size);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:user:select')")
    public PagedResult<User> search(PagedInputC<List<Search>> pagedInput) {
        return super.search(pagedInput);
    }



    @GetMapping("getUserInfo")
    @ApiOperation(value = "获取当前用户信息")
    public User getUser() {
        return userService.getUserInfo().setPassword(null);
    }


    @PostMapping("login")
    @ApiOperation(value = "登录")
    public JsonResultData<LoginResult> login(@RequestBody LoginInput input) {
        return userService.login(input);
    }

    @GetMapping("logout")
    @ApiOperation(value = "注销")
    public JsonResult logout() {
        return new JsonResult().Success();
    }
}