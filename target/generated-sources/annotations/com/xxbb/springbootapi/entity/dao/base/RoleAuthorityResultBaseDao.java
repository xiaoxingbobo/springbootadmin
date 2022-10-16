package com.xxbb.springbootapi.entity.dao.base;

import static com.xxbb.springbootapi.entity.helper.RoleAuthorityResultMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.entity.mapper.RoleAuthorityResultMapper;
import com.xxbb.springbootapi.entity.wrapper.RoleAuthorityResultQuery;
import com.xxbb.springbootapi.entity.wrapper.RoleAuthorityResultUpdate;
import javax.annotation.Resource;

/**
 *
 * RoleAuthorityResultBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class RoleAuthorityResultBaseDao extends BaseDao<RoleAuthorityResult, RoleAuthorityResultQuery, RoleAuthorityResultUpdate> {
  protected RoleAuthorityResultMapper mapper;

  @Override
  public RoleAuthorityResultMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmRoleAuthorityResultMapper"
  )
  public void setMapper(IMapper<RoleAuthorityResult> mapper) {
    this.mapper = (RoleAuthorityResultMapper)mapper;
  }
}
