package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.CommonMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.mapper.CommonMapper;
import com.xxbb.springbootapi.wrapper.CommonQuery;
import com.xxbb.springbootapi.wrapper.CommonUpdate;
import javax.annotation.Resource;

/**
 *
 * CommonBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class CommonBaseDao extends BaseDao<Common, CommonQuery, CommonUpdate> {
  protected CommonMapper mapper;

  @Override
  public CommonMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmCommonMapper"
  )
  public void setMapper(IMapper<Common> mapper) {
    this.mapper = (CommonMapper)mapper;
  }
}
