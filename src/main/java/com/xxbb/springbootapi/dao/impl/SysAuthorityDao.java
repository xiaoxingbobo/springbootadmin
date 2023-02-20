package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ISysSysAuthorityDao;
import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysAuthorityDao extends SysBaseDao<SysAuthority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> implements ISysSysAuthorityDao {

}
