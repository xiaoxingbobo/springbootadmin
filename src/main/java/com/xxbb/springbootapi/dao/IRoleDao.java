package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRoleDao extends IBaseDao<Role, RoleQuery, RoleUpdate, RoleMapper> {

}
