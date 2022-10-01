package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.entity.JsonResult;
import com.xxbb.springbootapi.mapper.AccountMapper;
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;

public interface IAccountService extends IBaseService<Account, AccountQuery, AccountUpdate, AccountMapper> {
    JsonResult Login(String number, String password);
}
