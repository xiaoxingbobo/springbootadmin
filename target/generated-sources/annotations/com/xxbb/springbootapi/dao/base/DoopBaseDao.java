package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.DoopMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.mapper.DoopMapper;
import com.xxbb.springbootapi.wrapper.DoopQuery;
import com.xxbb.springbootapi.wrapper.DoopUpdate;
import javax.annotation.Resource;

/**
 *
 * DoopBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class DoopBaseDao extends BaseDao<Doop, DoopQuery, DoopUpdate> {
  protected DoopMapper mapper;

  @Override
  public DoopMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmDoopMapper"
  )
  public void setMapper(IMapper<Doop> mapper) {
    this.mapper = (DoopMapper)mapper;
  }
}
