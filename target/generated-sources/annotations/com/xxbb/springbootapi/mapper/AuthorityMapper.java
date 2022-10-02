package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.AuthorityMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * AuthorityMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmAuthorityMapper")
public interface AuthorityMapper extends IWrapperMapper<Authority, AuthorityQuery, AuthorityUpdate>, IMapper<Authority> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
