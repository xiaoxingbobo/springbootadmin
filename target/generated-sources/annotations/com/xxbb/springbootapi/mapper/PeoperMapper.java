package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.PeoperMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.wrapper.PeoperQuery;
import com.xxbb.springbootapi.wrapper.PeoperUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * PeoperMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmPeoperMapper")
public interface PeoperMapper extends IWrapperMapper<Peoper, PeoperQuery, PeoperUpdate>, IMapper<Peoper> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
