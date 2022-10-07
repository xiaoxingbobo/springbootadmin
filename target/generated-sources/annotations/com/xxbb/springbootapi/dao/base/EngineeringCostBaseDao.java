package com.xxbb.springbootapi.dao.base;

import static com.xxbb.springbootapi.helper.EngineeringCostMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.IDefaultGetter;
import cn.org.atool.fluent.mybatis.base.dao.BaseDao;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import com.xxbb.springbootapi.entity.EngineeringCost;
import com.xxbb.springbootapi.mapper.EngineeringCostMapper;
import com.xxbb.springbootapi.wrapper.EngineeringCostQuery;
import com.xxbb.springbootapi.wrapper.EngineeringCostUpdate;
import javax.annotation.Resource;

/**
 *
 * EngineeringCostBaseDao
 *
 * @author powered by FluentMybatis
 */
public abstract class EngineeringCostBaseDao extends BaseDao<EngineeringCost, EngineeringCostQuery, EngineeringCostUpdate> {
  protected EngineeringCostMapper mapper;

  @Override
  public EngineeringCostMapper mapper() {
    return mapper;
  }

  @Override
  protected IDefaultGetter defaults() {
    return MAPPING;
  }

  @Override
  @Resource(
      name = "fmEngineeringCostMapper"
  )
  public void setMapper(IMapper<EngineeringCost> mapper) {
    this.mapper = (EngineeringCostMapper)mapper;
  }
}
