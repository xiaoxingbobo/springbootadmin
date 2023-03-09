package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysAuthorityDao;
import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.mapper.SysAuthorityMapper;
import com.xxbb.springbootapi.wrapper.SysAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysAuthorityUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysAuthorityDao extends SysBaseDao<SysAuthority, SysAuthorityQuery, SysAuthorityUpdate, SysAuthorityMapper> implements ISysSysAuthorityDao {

}
