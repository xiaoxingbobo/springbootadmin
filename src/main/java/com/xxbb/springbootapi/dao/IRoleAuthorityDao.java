package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRoleAuthorityDao extends IBaseDao<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> {

}
