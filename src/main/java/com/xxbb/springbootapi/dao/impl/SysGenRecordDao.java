package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysGenRecordDao;
import com.xxbb.springbootapi.entity.SysGenRecord;
import com.xxbb.springbootapi.mapper.SysGenRecordMapper;
import com.xxbb.springbootapi.wrapper.SysGenRecordQuery;
import com.xxbb.springbootapi.wrapper.SysGenRecordUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysGenRecordDao extends SysBaseDao<SysGenRecord, SysGenRecordQuery, SysGenRecordUpdate, SysGenRecordMapper> implements ISysSysGenRecordDao {

}
