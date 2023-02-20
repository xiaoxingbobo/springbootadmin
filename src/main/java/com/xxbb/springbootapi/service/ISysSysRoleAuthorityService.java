package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.entity.dto.SysRoleAuthorityResult;
import com.xxbb.springbootapi.mapper.SysRoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityUpdate;

import java.util.List;

public interface ISysSysRoleAuthorityService extends ISysBaseService<SysRoleAuthority, SysRoleAuthorityQuery, SysRoleAuthorityUpdate, SysRoleAuthorityMapper> {
    List<SysRoleAuthorityResult> listJoin(SysRoleAuthority entity);

    Boolean updateBatch(List<SysRoleAuthority> entities);
}
