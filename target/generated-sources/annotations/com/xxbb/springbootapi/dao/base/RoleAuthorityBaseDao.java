package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.RoleAuthorityMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import javax.annotation.Resource;

/**
 *
 * RoleAuthorityBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class RoleAuthorityBaseDao extends BaseDao<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate> {
  protected RoleAuthorityMapper mapper;

  @Override
  public RoleAuthorityMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmRoleAuthorityMapper"
  )
  public void setMapper(IMapper<RoleAuthority> mapper) {
    this.mapper = (RoleAuthorityMapper)mapper;
  }
}
