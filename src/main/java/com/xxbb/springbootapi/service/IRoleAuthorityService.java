package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;

import java.util.List;

public interface IRoleAuthorityService extends IBaseService<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> {
    List<RoleAuthorityResult> listJoin(RoleAuthority entity);
}
