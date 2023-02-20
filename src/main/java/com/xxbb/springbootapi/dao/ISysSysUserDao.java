package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.SysUser;
import com.xxbb.springbootapi.mapper.SysUserMapper;
import com.xxbb.springbootapi.wrapper.SysUserQuery;
import com.xxbb.springbootapi.wrapper.SysUserUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysSysUserDao extends ISysBaseDao<SysUser, SysUserQuery, SysUserUpdate, SysUserMapper> {

}