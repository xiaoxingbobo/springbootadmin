package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.HaMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.Ha;
import com.xxbb.springbootapi.wrapper.HaQuery;
import com.xxbb.springbootapi.wrapper.HaUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * HaMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmHaMapper")
public interface HaMapper extends IWrapperMapper<Ha, HaQuery, HaUpdate>, IMapper<Ha> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
