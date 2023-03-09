package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.mapper.SysAuthorityMapper;
import com.xxbb.springbootapi.wrapper.SysAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysAuthorityUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysSysAuthorityDao extends ISysBaseDao<SysAuthority, SysAuthorityQuery, SysAuthorityUpdate, SysAuthorityMapper> {

}
