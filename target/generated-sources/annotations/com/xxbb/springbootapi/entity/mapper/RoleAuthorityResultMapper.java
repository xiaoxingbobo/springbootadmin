package com.xxbb.springbootapi.entity.mapper;

import static com.xxbb.springbootapi.entity.helper.RoleAuthorityResultMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.entity.wrapper.RoleAuthorityResultQuery;
import com.xxbb.springbootapi.entity.wrapper.RoleAuthorityResultUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * RoleAuthorityResultMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmRoleAuthorityResultMapper")
public interface RoleAuthorityResultMapper extends IWrapperMapper<RoleAuthorityResult, RoleAuthorityResultQuery, RoleAuthorityResultUpdate>, IMapper<RoleAuthorityResult> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
