package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.entity.JsonResult;
import com.xxbb.springbootapi.entity.dto.loginDTO;
import com.xxbb.springbootapi.mapper.AccountMapper;
import com.xxbb.springbootapi.service.impl.AccountService;
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("Accounts")
@Api(tags = "Account表")
@RestController
public class AccountController extends ApiController<Account, AccountQuery, AccountUpdate, AccountMapper> {
    @Autowired(required = false)
    private AccountService service;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public JsonResult Login(@RequestBody loginDTO dto) {

        return service.Login(dto.getAccount(), dto.getPassword());
    }
}
