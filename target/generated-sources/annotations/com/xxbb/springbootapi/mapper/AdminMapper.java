package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.AdminMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.wrapper.AdminQuery;
import com.xxbb.springbootapi.wrapper.AdminUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * AdminMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmAdminMapper")
public interface AdminMapper extends IWrapperMapper<Admin, AdminQuery, AdminUpdate>, IMapper<Admin> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
