package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.CommonMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.wrapper.CommonQuery;
import com.xxbb.springbootapi.wrapper.CommonUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * CommonMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmCommonMapper")
public interface CommonMapper extends IWrapperMapper<Common, CommonQuery, CommonUpdate>, IMapper<Common> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
