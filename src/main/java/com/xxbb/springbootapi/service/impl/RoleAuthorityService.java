package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.service.IRoleAuthorityService;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthorityService extends BaseService<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> implements IRoleAuthorityService {

    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员角色权限不允许删除");
        }
        return super.delete(id);
    }
}
