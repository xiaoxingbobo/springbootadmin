package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysRoleDao;
import com.xxbb.springbootapi.entity.SysRole;
import com.xxbb.springbootapi.mapper.SysRoleMapper;
import com.xxbb.springbootapi.wrapper.SysRoleQuery;
import com.xxbb.springbootapi.wrapper.SysRoleUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysRoleDao extends SysBaseDao<SysRole, SysRoleQuery, SysRoleUpdate, SysRoleMapper> implements ISysSysRoleDao {

}
