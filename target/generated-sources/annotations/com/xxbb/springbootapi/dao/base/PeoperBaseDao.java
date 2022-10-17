package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.PeoperMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.mapper.PeoperMapper;
import com.xxbb.springbootapi.wrapper.PeoperQuery;
import com.xxbb.springbootapi.wrapper.PeoperUpdate;
import javax.annotation.Resource;

/**
 *
 * PeoperBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class PeoperBaseDao extends BaseDao<Peoper, PeoperQuery, PeoperUpdate> {
  protected PeoperMapper mapper;

  @Override
  public PeoperMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmPeoperMapper"
  )
  public void setMapper(IMapper<Peoper> mapper) {
    this.mapper = (PeoperMapper)mapper;
  }
}
