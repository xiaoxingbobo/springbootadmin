package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.PeopleMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.mapper.PeopleMapper;
import com.xxbb.springbootapi.wrapper.PeopleQuery;
import com.xxbb.springbootapi.wrapper.PeopleUpdate;
import javax.annotation.Resource;

/**
 *
 * PeopleBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class PeopleBaseDao extends BaseDao<People, PeopleQuery, PeopleUpdate> {
  protected PeopleMapper mapper;

  @Override
  public PeopleMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmPeopleMapper"
  )
  public void setMapper(IMapper<People> mapper) {
    this.mapper = (PeopleMapper)mapper;
  }
}
