package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.CodeGenRecordMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.mapper.CodeGenRecordMapper;
import com.xxbb.springbootapi.wrapper.CodeGenRecordQuery;
import com.xxbb.springbootapi.wrapper.CodeGenRecordUpdate;
import javax.annotation.Resource;

/**
 *
 * CodeGenRecordBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class CodeGenRecordBaseDao extends BaseDao<GenRecord, CodeGenRecordQuery, CodeGenRecordUpdate> {
  protected CodeGenRecordMapper mapper;

  @Override
  public CodeGenRecordMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmCodeGenRecordMapper"
  )
  public void setMapper(IMapper<GenRecord> mapper) {
    this.mapper = (CodeGenRecordMapper)mapper;
  }
}
