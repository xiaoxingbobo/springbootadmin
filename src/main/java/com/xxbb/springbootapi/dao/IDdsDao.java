package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Dds;
import com.xxbb.springbootapi.mapper.DdsMapper;
import com.xxbb.springbootapi.wrapper.DdsQuery;
import com.xxbb.springbootapi.wrapper.DdsUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IDdsDao extends IBaseDao<Dds, DdsQuery, DdsUpdate, DdsMapper> {
}
