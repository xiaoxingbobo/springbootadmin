package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.entity.dto.SysRoleAuthorityResult;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.SysRoleAuthorityMapper;
import com.xxbb.springbootapi.service.ISysRoleAuthorityService;
import com.xxbb.springbootapi.utils.OrikaUtil;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SysRoleAuthorityService extends SysBaseService<SysRoleAuthority, SysRoleAuthorityQuery, SysRoleAuthorityUpdate, SysRoleAuthorityMapper> implements ISysRoleAuthorityService {

    @Autowired
    private SysAuthorityService sysAuthorityService;
    @Autowired
    private SysRoleAuthorityService service;

    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员角色权限不允许删除");
        }
        return super.delete(id);
    }

    @Override
    public List<SysRoleAuthorityResult> listJoin(SysRoleAuthority entity) {
        List<SysRoleAuthority> roleAuthorityList = dao.list(entity);
        List<SysRoleAuthorityResult> roleAuthorityResultList = OrikaUtil.converts(roleAuthorityList, SysRoleAuthorityResult.class);
        //获取ids
        List<Integer> ids = roleAuthorityList.stream().map(SysRoleAuthority::getAuthorityId).collect(Collectors.toList());
        //查询权限值
        List<SysAuthority> permissions = sysAuthorityService.list(ids);
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
    public Boolean updateBatch(List<SysRoleAuthority> entities) {
        Integer roleId = entities.get(0).getRoleId();
        //删除原有权限
        mapper().delete(mapper().query().where.roleId().eq(roleId).end());
        //添加新权限
        return service.add(entities);
    }
}
