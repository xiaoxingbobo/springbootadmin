package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysRoleAuthorityDao;
import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.mapper.SysRoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysRoleAuthorityDao extends SysBaseDao<SysRoleAuthority, SysRoleAuthorityQuery, SysRoleAuthorityUpdate, SysRoleAuthorityMapper> implements ISysSysRoleAuthorityDao {

}
