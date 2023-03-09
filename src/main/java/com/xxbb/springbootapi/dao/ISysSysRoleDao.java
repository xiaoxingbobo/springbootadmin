package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.SysRole;
import com.xxbb.springbootapi.mapper.SysRoleMapper;
import com.xxbb.springbootapi.wrapper.SysRoleQuery;
import com.xxbb.springbootapi.wrapper.SysRoleUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysSysRoleDao extends ISysBaseDao<SysRole, SysRoleQuery, SysRoleUpdate, SysRoleMapper> {

}
