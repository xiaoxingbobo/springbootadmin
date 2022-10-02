package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.AuthorityMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import javax.annotation.Resource;

/**
 *
 * AuthorityBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class AuthorityBaseDao extends BaseDao<Authority, AuthorityQuery, AuthorityUpdate> {
  protected AuthorityMapper mapper;

  @Override
  public AuthorityMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmAuthorityMapper"
  )
  public void setMapper(IMapper<Authority> mapper) {
    this.mapper = (AuthorityMapper)mapper;
  }
}
