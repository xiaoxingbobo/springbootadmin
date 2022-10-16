package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.HaMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Ha;
import com.xxbb.springbootapi.mapper.HaMapper;
import com.xxbb.springbootapi.wrapper.HaQuery;
import com.xxbb.springbootapi.wrapper.HaUpdate;
import javax.annotation.Resource;

/**
 *
 * HaBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class HaBaseDao extends BaseDao<Ha, HaQuery, HaUpdate> {
  protected HaMapper mapper;

  @Override
  public HaMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmHaMapper"
  )
  public void setMapper(IMapper<Ha> mapper) {
    this.mapper = (HaMapper)mapper;
  }
}
