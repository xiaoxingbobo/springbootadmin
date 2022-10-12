package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.service.IRoleService;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, RoleQuery, RoleUpdate, RoleMapper> implements IRoleService {

    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员角色不允许删除");
        }
        return super.delete(id);
    }
}
