package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.AccountMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * AccountMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmAccountMapper")
public interface AccountMapper extends IWrapperMapper<Account, AccountQuery, AccountUpdate>, IMapper<Account> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
