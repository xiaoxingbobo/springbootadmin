package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.mapper.AccountMapper;
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAccountDao extends IBaseDao<Account, AccountQuery, AccountUpdate, AccountMapper> {
}
