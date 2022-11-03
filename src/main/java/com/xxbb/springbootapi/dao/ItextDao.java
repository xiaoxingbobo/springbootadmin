package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.text;
import com.xxbb.springbootapi.mapper.textMapper;
import com.xxbb.springbootapi.wrapper.textQuery;
import com.xxbb.springbootapi.wrapper.textUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItextDao extends IBaseDao<text, textQuery, textUpdate, textMapper> {
}
