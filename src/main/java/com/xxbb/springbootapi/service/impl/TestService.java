package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.service.ISysTestService;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;
import org.springframework.stereotype.Service;

@Service
public class TestService extends SysBaseService<Test, TestQuery, TestUpdate, TestMapper> implements ISysTestService {

}

