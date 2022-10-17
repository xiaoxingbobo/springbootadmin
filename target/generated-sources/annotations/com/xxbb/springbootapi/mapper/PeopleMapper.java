package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.PeopleMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.wrapper.PeopleQuery;
import com.xxbb.springbootapi.wrapper.PeopleUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * PeopleMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmPeopleMapper")
public interface PeopleMapper extends IWrapperMapper<People, PeopleQuery, PeopleUpdate>, IMapper<People> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
