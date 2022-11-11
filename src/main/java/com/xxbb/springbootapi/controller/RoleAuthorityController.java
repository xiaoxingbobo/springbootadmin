package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.entity.dto.Search;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.service.impl.RoleAuthorityService;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("RoleAuthoritys")
@Api(tags = "RoleAuthority表")
@RestController
public class RoleAuthorityController extends AuthApiController<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate, RoleAuthorityMapper> {


    @Autowired
    RoleAuthorityService service;

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:add')")
    public Boolean create(RoleAuthority entity) {
        return super.create(entity);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:delete')")
    public Boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:delte')")
    public Boolean delete(List<Integer> ids) {
        return super.delete(ids);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:update')")
    public Boolean update(RoleAuthority entity) {
        return super.update(entity);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:select')")
    public List<RoleAuthority> select(List<Search> searches) {
        return super.select(searches);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:select')")
    public RoleAuthority find(int id) {
        return super.find(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:select')")
    public List<RoleAuthority> select(int limit) {
        return super.select(limit);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:select')")
    public List<RoleAuthority> select() {
        return super.select();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:select')")
    public PagedResult<RoleAuthority> select(PagedInputC<RoleAuthority> input) {
        return super.select(input);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:select')")
    public PagedResult<RoleAuthority> paged(int current, int size) {
        return super.paged(current, size);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:select')")
    public PagedResult<RoleAuthority> search(PagedInputC<List<Search>> pagedInput) {
        return super.search(pagedInput);
    }

    @PostMapping("/listJoin")
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:select')")
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
    @PutMapping ("batch")
    @PreAuthorize("hasAnyAuthority('sys:roleAuthority:update')")
    @ApiOperation(value = "批量修改", notes = "id，创建时间，修改时间无需提交")
    public Boolean updateBatch(@RequestBody List<RoleAuthority> entities) {
        return service.updateBatch(entities);
    }
}
