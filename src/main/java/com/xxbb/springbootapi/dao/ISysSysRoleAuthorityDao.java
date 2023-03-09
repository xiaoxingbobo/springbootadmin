package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.mapper.SysRoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysSysRoleAuthorityDao extends ISysBaseDao<SysRoleAuthority, SysRoleAuthorityQuery, SysRoleAuthorityUpdate, SysRoleAuthorityMapper> {

}
