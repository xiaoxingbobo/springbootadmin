package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.mapper.DoopMapper;
import com.xxbb.springbootapi.wrapper.DoopQuery;
import com.xxbb.springbootapi.wrapper.DoopUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IDoopDao extends IBaseDao<Doop, DoopQuery, DoopUpdate, DoopMapper> {
}
