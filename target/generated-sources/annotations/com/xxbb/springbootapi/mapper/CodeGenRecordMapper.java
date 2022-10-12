package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.CodeGenRecordMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.wrapper.CodeGenRecordQuery;
import com.xxbb.springbootapi.wrapper.CodeGenRecordUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * CodeGenRecordMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmCodeGenRecordMapper")
public interface CodeGenRecordMapper extends IWrapperMapper<GenRecord, CodeGenRecordQuery, CodeGenRecordUpdate>, IMapper<GenRecord> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}
