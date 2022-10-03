package com.xxbb.springbootapi.controller;


import com.xxbb.springbootapi.config.AppConfig;
import com.xxbb.springbootapi.entity.JsonResult;
import com.xxbb.springbootapi.entity.JsonResultData;
import com.xxbb.springbootapi.entity.dto.LoginInput;
import com.xxbb.springbootapi.entity.dto.LoginResult;
import com.xxbb.springbootapi.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("index")
@Api(tags = "index首页")
@RestController
public class IndexController {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private UserService service;

    @GetMapping("index")
    @ApiOperation(value = "首页")
    public JsonResult index() {
        return new JsonResult().Success();
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public JsonResult login(@RequestBody LoginInput input) {
        return service.login(input);
    }
    @GetMapping("login")
    @ApiOperation(value = "登录")
    public JsonResult login() {
        return new JsonResultData<>().Success(new LoginResult().setCaptcha(true));
    }
}
