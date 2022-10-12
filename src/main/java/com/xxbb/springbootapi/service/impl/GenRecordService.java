package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.service.IGenRecordService;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import org.springframework.stereotype.Service;

@Service
public class GenRecordService extends BaseService<GenRecord, GenRecordQuery, GenRecordUpdate, GenRecordMapper> implements IGenRecordService {

}

