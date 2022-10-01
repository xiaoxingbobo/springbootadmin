package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;

public interface ITestService extends IBaseService<Test, TestQuery, TestUpdate, TestMapper> {

}
