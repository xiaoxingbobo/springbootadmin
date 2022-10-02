package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.RoleMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;
import javax.annotation.Resource;

/**
 *
 * RoleBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class RoleBaseDao extends BaseDao<Role, RoleQuery, RoleUpdate> {
  protected RoleMapper mapper;

  @Override
  public RoleMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmRoleMapper"
  )
  public void setMapper(IMapper<Role> mapper) {
    this.mapper = (RoleMapper)mapper;
  }
}
