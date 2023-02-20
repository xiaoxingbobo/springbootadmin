package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.SysGenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("genRecord")
@Api(tags = "GenRecord表")
@RestController
public class GenRecordController extends AuthApiController<SysGenRecord, GenRecordQuery, GenRecordUpdate, GenRecordMapper> {

}
