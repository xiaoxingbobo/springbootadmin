package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;

public interface ISysTestService extends ISysBaseService<Test, TestQuery, TestUpdate, TestMapper> {

}
