package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.service.IRoleAuthorityService;
import com.xxbb.springbootapi.utils.OrikaUtil;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleAuthorityService extends BaseService<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> implements IRoleAuthorityService {

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleAuthorityService service;

    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员角色权限不允许删除");
        }
        return super.delete(id);
    }

    @Override
    public List<RoleAuthorityResult> listJoin(RoleAuthority entity) {
        List<RoleAuthority> roleAuthorityList = dao.list(entity);
        List<RoleAuthorityResult> roleAuthorityResultList = OrikaUtil.converts(roleAuthorityList, RoleAuthorityResult.class);
        //获取ids
        List<Integer> ids = roleAuthorityList.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        //查询权限值
        List<Authority> permissions = authorityService.list(ids);
        //设置权限值
        //使用stream()关联
        roleAuthorityResultList.stream().peek(roleAuthorityResult -> {
            //设置权限值
            roleAuthorityResult.setAuthorityValue(Objects.requireNonNull(permissions.stream().filter(authority -> authority.getId().equals(roleAuthorityResult.getAuthorityId())).findFirst().orElse(null)).getValue());
            roleAuthorityResult.setAuthorityName(Objects.requireNonNull(permissions.stream().filter(authority -> authority.getId().equals(roleAuthorityResult.getAuthorityId())).findFirst().orElse(null)).getName());
        }).collect(Collectors.toList());


//        roleAuthorityResultList.forEach(roleAuthorityResult -> {
//            permissions.forEach(authority -> {
//                if (roleAuthorityResult.getAuthorityId().equals(authority.getId())) {
//                    roleAuthorityResult.setAuthorityValue(authority.getValue());
//                    roleAuthorityResult.setAuthorityName(authority.getName());
//                }
//            });
//        });
        return roleAuthorityResultList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBatch(List<RoleAuthority> entities) {
        Integer roleId = entities.get(0).getRoleId();
        //删除原有权限
        mapper().delete(mapper().query().where.roleId().eq(roleId).end());
        //查询是否包含超级管理员权限
        List<RoleAuthority> adminRoleAuth = entities.stream().filter(roleAuthority -> roleAuthority.getAuthorityId() == 1 && roleAuthority.getRoleId() == 1).collect(Collectors.toList());
        //添加超级管理员权限
        if (adminRoleAuth.size()==0) {
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRoleId(1);
            roleAuthority.setAuthorityId(1);
            entities.add(roleAuthority);
        }
        //添加新权限
        return service.add(entities);
    }
}
