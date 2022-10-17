package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IDoopDao;
import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.mapper.DoopMapper;
import com.xxbb.springbootapi.wrapper.DoopQuery;
import com.xxbb.springbootapi.wrapper.DoopUpdate;
import org.springframework.stereotype.Service;

@Service
public class DoopDao extends BaseDao<Doop, DoopQuery, DoopUpdate, DoopMapper> implements IDoopDao {

}
