package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.TestMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;
import javax.annotation.Resource;

/**
 *
 * TestBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class TestBaseDao extends BaseDao<Test, TestQuery, TestUpdate> {
  protected TestMapper mapper;

  @Override
  public TestMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmTestMapper"
  )
  public void setMapper(IMapper<Test> mapper) {
    this.mapper = (TestMapper)mapper;
  }
}
