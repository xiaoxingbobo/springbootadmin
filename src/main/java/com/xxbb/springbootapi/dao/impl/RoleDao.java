package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IRoleDao;
import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;
import org.springframework.stereotype.Service;

@Service
public class RoleDao extends BaseDao<Role, RoleQuery, RoleUpdate, RoleMapper> implements IRoleDao {

}
