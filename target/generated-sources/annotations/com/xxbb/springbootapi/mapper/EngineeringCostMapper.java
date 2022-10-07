package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.EngineeringCostMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.EngineeringCost;
import com.xxbb.springbootapi.wrapper.EngineeringCostQuery;
import com.xxbb.springbootapi.wrapper.EngineeringCostUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * EngineeringCostMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmEngineeringCostMapper")
public interface EngineeringCostMapper extends IWrapperMapper<EngineeringCost, EngineeringCostQuery, EngineeringCostUpdate>, IMapper<EngineeringCost> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
