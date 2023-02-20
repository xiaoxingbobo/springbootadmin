package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysTestDao;
import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;
import org.springframework.stereotype.Service;

@Service
public class TestDao extends SysBaseDao<Test, TestQuery, TestUpdate, TestMapper> implements ISysTestDao {

}
