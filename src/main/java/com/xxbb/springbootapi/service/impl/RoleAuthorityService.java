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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleAuthorityService extends BaseService<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> implements IRoleAuthorityService {

    @Autowired
    private AuthorityService authorityService;

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
}
