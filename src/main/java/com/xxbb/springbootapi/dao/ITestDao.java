package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITestDao extends IBaseDao<Test, TestQuery, TestUpdate, TestMapper> {
}
