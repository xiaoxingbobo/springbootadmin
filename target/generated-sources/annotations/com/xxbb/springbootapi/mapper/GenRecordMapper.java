package com.xxbb.springbootapi.mapper;

import static com.xxbb.springbootapi.helper.GenRecordMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.mapper.PrinterMapper;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import java.util.List;
import java.util.function.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * GenRecordMapper: Mapper接口
 *
 * @author powered by FluentMybatis
 */
@Mapper
@Component("fmGenRecordMapper")
public interface GenRecordMapper extends IWrapperMapper<GenRecord, GenRecordQuery, GenRecordUpdate>, IMapper<GenRecord> {
  @Override
  default IMapping mapping() {
    return MAPPING;
  }

  static List<String> print(int mode, Consumer<IWrapperMapper> simulators) {
    return PrinterMapper.print(mode, MAPPING, simulators);
  }
}