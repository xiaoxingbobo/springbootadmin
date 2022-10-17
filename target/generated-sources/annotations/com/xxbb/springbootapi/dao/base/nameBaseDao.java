package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.nameMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.mapper.nameMapper;
import com.xxbb.springbootapi.wrapper.nameQuery;
import com.xxbb.springbootapi.wrapper.nameUpdate;
import javax.annotation.Resource;

/**
 *
 * nameBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class nameBaseDao extends BaseDao<name, nameQuery, nameUpdate> {
  protected nameMapper mapper;

  @Override
  public nameMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmnameMapper"
  )
  public void setMapper(IMapper<name> mapper) {
    this.mapper = (nameMapper)mapper;
  }
}
