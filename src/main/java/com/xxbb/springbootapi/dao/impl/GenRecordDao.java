package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IGenRecordDao;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import org.springframework.stereotype.Service;

@Service
public class GenRecordDao extends BaseDao<GenRecord, GenRecordQuery, GenRecordUpdate, GenRecordMapper> implements IGenRecordDao {

}
