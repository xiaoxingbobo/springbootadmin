package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysSysRoleAuthorityDao extends ISysBaseDao<SysRoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> {

}
