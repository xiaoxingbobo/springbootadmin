package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.GenRecordMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import javax.annotation.Resource;

/**
 *
 * GenRecordBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class GenRecordBaseDao extends BaseDao<GenRecord, GenRecordQuery, GenRecordUpdate> {
  protected GenRecordMapper mapper;

  @Override
  public GenRecordMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmGenRecordMapper"
  )
  public void setMapper(IMapper<GenRecord> mapper) {
    this.mapper = (GenRecordMapper)mapper;
  }
}
