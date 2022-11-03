package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.service.impl.RoleAuthorityService;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("RoleAuthoritys")
@Api(tags = "RoleAuthority表")
@RestController
public class RoleAuthorityController extends AuthApiController<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> {


    @Autowired
    RoleAuthorityService service;

    @PostMapping("/listJoin")
    @PreAuthorize("hasAnyAuthority('sys:all:all')")
    @ApiOperation(value = "连接查询列表", notes = "id，创建时间，修改时间无需提交")
    public List<RoleAuthorityResult> listJoin(@RequestBody RoleAuthority entity) {
        return service.listJoin(entity);
    }

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @PostMapping("batch")
    @PreAuthorize("hasAnyAuthority('sys:all:all')")
    @ApiOperation(value = "批量添加", notes = "id，创建时间，修改时间无需提交")
    public Boolean createBatch(@RequestBody List<RoleAuthority> entities) {
        return service.add(entities);
    }
}
