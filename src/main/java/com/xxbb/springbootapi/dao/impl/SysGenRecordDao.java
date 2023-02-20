package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysGenRecordDao;
import com.xxbb.springbootapi.entity.SysGenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysGenRecordDao extends SysBaseDao<SysGenRecord, GenRecordQuery, GenRecordUpdate, GenRecordMapper> implements ISysSysGenRecordDao {

}
