package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.AdminMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Admin;
import com.xxbb.springbootapi.mapper.AdminMapper;
import com.xxbb.springbootapi.wrapper.AdminQuery;
import com.xxbb.springbootapi.wrapper.AdminUpdate;
import javax.annotation.Resource;

/**
 *
 * AdminBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class AdminBaseDao extends BaseDao<Admin, AdminQuery, AdminUpdate> {
  protected AdminMapper mapper;

  @Override
  public AdminMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmAdminMapper"
  )
  public void setMapper(IMapper<Admin> mapper) {
    this.mapper = (AdminMapper)mapper;
  }
}
