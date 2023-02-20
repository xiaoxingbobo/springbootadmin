package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysRoleAuthorityDao;
import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysRoleAuthorityDao extends SysBaseDao<SysRoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> implements ISysSysRoleAuthorityDao {

}
