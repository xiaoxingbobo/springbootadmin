package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysRoleDao;
import com.xxbb.springbootapi.entity.SysRole;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysRoleDao extends SysBaseDao<SysRole, RoleQuery, RoleUpdate, RoleMapper> implements ISysSysRoleDao {

}
