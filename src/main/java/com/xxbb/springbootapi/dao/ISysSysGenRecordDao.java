package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.SysGenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysSysGenRecordDao extends ISysBaseDao<SysGenRecord, GenRecordQuery, GenRecordUpdate, GenRecordMapper> {
}
