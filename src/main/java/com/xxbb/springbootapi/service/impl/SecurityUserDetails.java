package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SecurityUserDetails implements UserDetails {
    private SysUser sysUser;
    private List<String> permissions;

    private List<SimpleGrantedAuthority> collect;

    /**
     * 有参数构造
     */
    public SecurityUserDetails(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
        this.collect = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /**
     * 获取当前用户的权限
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (collect == null) {
            collect = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return collect;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
