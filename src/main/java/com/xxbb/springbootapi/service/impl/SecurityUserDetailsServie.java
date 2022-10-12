package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SecurityUserDetailsServie implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findOne(userMapper.query().where.username().eq(s).end());
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //获取用户权限ids
        List<Integer> roleAuthorityIds = roleAuthorityService.list(new RoleAuthority().setRoleId(user.getRoleId())).stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        //查询权限值
        List<String> permissions = authorityService.list(roleAuthorityIds).stream().map(com.xxbb.springbootapi.entity.Authority::getValue).collect(Collectors.toList());
        return new SecurityUserDetails(user, permissions);
    }


}
