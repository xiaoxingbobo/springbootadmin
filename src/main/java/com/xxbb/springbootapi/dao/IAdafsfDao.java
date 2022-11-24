package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Adafsf;
import com.xxbb.springbootapi.mapper.AdafsfMapper;
import com.xxbb.springbootapi.wrapper.AdafsfQuery;
import com.xxbb.springbootapi.wrapper.AdafsfUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdafsfDao extends IBaseDao<Adafsf, AdafsfQuery, AdafsfUpdate, AdafsfMapper> {
}
