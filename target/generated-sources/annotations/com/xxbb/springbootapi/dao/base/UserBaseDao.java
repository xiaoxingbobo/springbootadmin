package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.UserMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import javax.annotation.Resource;

/**
 *
 * UserBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class UserBaseDao extends BaseDao<User, UserQuery, UserUpdate> {
  protected UserMapper mapper;

  @Override
  public UserMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmUserMapper"
  )
  public void setMapper(IMapper<User> mapper) {
    this.mapper = (UserMapper)mapper;
  }
}
