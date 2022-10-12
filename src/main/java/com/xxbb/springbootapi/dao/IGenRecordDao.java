package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IGenRecordDao extends IBaseDao<GenRecord, GenRecordQuery, GenRecordUpdate, GenRecordMapper> {
}
