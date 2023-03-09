package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.SysGenRecord;
import com.xxbb.springbootapi.mapper.SysGenRecordMapper;
import com.xxbb.springbootapi.wrapper.SysGenRecordQuery;
import com.xxbb.springbootapi.wrapper.SysGenRecordUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysSysGenRecordDao extends ISysBaseDao<SysGenRecord, SysGenRecordQuery, SysGenRecordUpdate, SysGenRecordMapper> {
}
