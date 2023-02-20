package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.SysRole;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.SysRoleMapper;
import com.xxbb.springbootapi.service.ISysSysRoleService;
import com.xxbb.springbootapi.wrapper.SysRoleQuery;
import com.xxbb.springbootapi.wrapper.SysRoleUpdate;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService extends SysBaseService<SysRole, SysRoleQuery, SysRoleUpdate, SysRoleMapper> implements ISysSysRoleService {

    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员角色不允许删除");
        }
        return super.delete(id);
    }
}
