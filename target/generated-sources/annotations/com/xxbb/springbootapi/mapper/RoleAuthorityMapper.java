package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.RoleAuthorityMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * RoleAuthorityMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmRoleAuthorityMapper")
public interface RoleAuthorityMapper extends IWrapperMapper<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate>, IMapper<RoleAuthority> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
