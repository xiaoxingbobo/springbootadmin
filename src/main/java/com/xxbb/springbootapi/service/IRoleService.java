package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;

public interface IRoleService extends IBaseService<Role, RoleQuery, RoleUpdate, RoleMapper> {

}
