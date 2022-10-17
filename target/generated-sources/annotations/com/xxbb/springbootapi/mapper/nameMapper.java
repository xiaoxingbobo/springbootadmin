package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.nameMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.wrapper.nameQuery;
import com.xxbb.springbootapi.wrapper.nameUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * nameMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmnameMapper")
public interface nameMapper extends IWrapperMapper<name, nameQuery, nameUpdate>, IMapper<name> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
