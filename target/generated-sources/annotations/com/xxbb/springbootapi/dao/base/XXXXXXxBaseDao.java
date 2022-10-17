package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.XXXXXXxMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.mapper.XXXXXXxMapper;
import com.xxbb.springbootapi.wrapper.XXXXXXxQuery;
import com.xxbb.springbootapi.wrapper.XXXXXXxUpdate;
import javax.annotation.Resource;

/**
 *
 * XXXXXXxBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class XXXXXXxBaseDao extends BaseDao<XXXXXXx, XXXXXXxQuery, XXXXXXxUpdate> {
  protected XXXXXXxMapper mapper;

  @Override
  public XXXXXXxMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmXXXXXXxMapper"
  )
  public void setMapper(IMapper<XXXXXXx> mapper) {
    this.mapper = (XXXXXXxMapper)mapper;
  }
}
