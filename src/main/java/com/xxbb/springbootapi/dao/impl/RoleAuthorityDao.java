package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IRoleAuthorityDao;
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthorityDao extends BaseDao<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> implements IRoleAuthorityDao {

}
