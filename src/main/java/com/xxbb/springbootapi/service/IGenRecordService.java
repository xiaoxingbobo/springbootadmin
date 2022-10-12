package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;

public interface IGenRecordService extends IBaseService<GenRecord, GenRecordQuery, GenRecordUpdate, GenRecordMapper> {

}
