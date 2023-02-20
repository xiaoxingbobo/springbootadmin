package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.SysGenRecord;
import com.xxbb.springbootapi.mapper.SysGenRecordMapper;
import com.xxbb.springbootapi.service.ISysSysGenRecordService;
import com.xxbb.springbootapi.wrapper.SysGenRecordQuery;
import com.xxbb.springbootapi.wrapper.SysGenRecordUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysGenRecordService extends SysBaseService<SysGenRecord, SysGenRecordQuery, SysGenRecordUpdate, SysGenRecordMapper> implements ISysSysGenRecordService {

}

