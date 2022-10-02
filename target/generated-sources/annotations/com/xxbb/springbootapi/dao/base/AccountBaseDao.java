package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.AccountMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.mapper.AccountMapper;
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;
import javax.annotation.Resource;

/**
 *
 * AccountBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class AccountBaseDao extends BaseDao<Account, AccountQuery, AccountUpdate> {
  protected AccountMapper mapper;

  @Override
  public AccountMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmAccountMapper"
  )
  public void setMapper(IMapper<Account> mapper) {
    this.mapper = (AccountMapper)mapper;
  }
}
