package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.mapper.PeopleMapper;
import com.xxbb.springbootapi.wrapper.PeopleQuery;
import com.xxbb.springbootapi.wrapper.PeopleUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPeopleDao extends IBaseDao<People, PeopleQuery, PeopleUpdate, PeopleMapper> {
}
