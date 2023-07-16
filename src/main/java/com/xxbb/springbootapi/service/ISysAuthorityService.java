package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.entity.dto.MenuDTO;
import com.xxbb.springbootapi.mapper.SysAuthorityMapper;
import com.xxbb.springbootapi.wrapper.SysAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysAuthorityUpdate;

import java.util.List;

public interface ISysAuthorityService extends ISysBaseService<SysAuthority, SysAuthorityQuery, SysAuthorityUpdate, SysAuthorityMapper> {
    List<MenuDTO> viewMenu();
}
