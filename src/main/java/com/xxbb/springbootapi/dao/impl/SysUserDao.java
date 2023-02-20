package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysUserDao;
import com.xxbb.springbootapi.entity.SysUser;
import com.xxbb.springbootapi.mapper.SysUserMapper;
import com.xxbb.springbootapi.wrapper.SysUserQuery;
import com.xxbb.springbootapi.wrapper.SysUserUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysUserDao extends SysBaseDao<SysUser, SysUserQuery, SysUserUpdate, SysUserMapper> implements ISysSysUserDao {

}