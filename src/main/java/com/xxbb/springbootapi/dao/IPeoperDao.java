package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.mapper.PeoperMapper;
import com.xxbb.springbootapi.wrapper.PeoperQuery;
import com.xxbb.springbootapi.wrapper.PeoperUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPeoperDao extends IBaseDao<Peoper, PeoperQuery, PeoperUpdate, PeoperMapper> {
}
