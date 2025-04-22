package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.entity.SysUser;
import com.xxbb.springbootapi.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SecurityUserDetailsServie implements UserDetailsService {
    @Qualifier("fmSysUserMapper")
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleAuthorityService sysRoleAuthorityService;
    @Autowired
    private SysAuthorityService sysAuthorityService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.findOne(userMapper.query().where.username().eq(s).end());
        if (Objects.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //获取用户权限ids
        List<Integer> roleAuthorityIds = sysRoleAuthorityService.list(new SysRoleAuthority().setRoleId(sysUser.getRoleId())).stream().map(SysRoleAuthority::getAuthorityId).collect(Collectors.toList());
        //查询权限值
        List<String> permissions = sysAuthorityService.list(roleAuthorityIds).stream().map(SysAuthority::getValue).collect(Collectors.toList());
        return new SecurityUserDetails(sysUser, permissions);
    }
}
