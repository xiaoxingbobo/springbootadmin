package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IAccountDao;
import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.mapper.AccountMapper;
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;
import org.springframework.stereotype.Service;

@Service
public class AccountDao extends BaseDao<Account, AccountQuery, AccountUpdate, AccountMapper> implements IAccountDao {

}
