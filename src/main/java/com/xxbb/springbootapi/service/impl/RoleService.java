package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.service.IRoleService;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, RoleQuery, RoleUpdate, RoleMapper> implements IRoleService {

}
