package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.SysRole;
import com.xxbb.springbootapi.entity.dto.PagedInputT;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.mapper.SysRoleMapper;
import com.xxbb.springbootapi.service.impl.SysRoleService;
import com.xxbb.springbootapi.wrapper.SysRoleQuery;
import com.xxbb.springbootapi.wrapper.SysRoleUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("role")
@Api(tags = "Role表")
@RestController
public class RoleController extends AuthApiController<SysRole, SysRoleQuery, SysRoleUpdate, SysRoleMapper> {
    @Autowired
    private SysRoleService service;

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @Override
    @PostMapping
    @PreAuthorize("@auth.hasAuth('sys:role:add')")
    @ApiOperation(value = "添加", notes = "id，创建时间，修改时间无需提交")
    public Boolean create(@RequestBody SysRole entity) {
        return service.add(entity);
    }

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("@auth.hasAuth('sys:role:delete')")
    @ApiOperation(value = "删除")
    public Boolean delete(@PathVariable("id") int id) {
        return service.delete(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    @PostMapping("/delete")
    @PreAuthorize("@auth.hasAuth('sys:role:delete')")
    @ApiOperation(value = "批量删除")
    public Boolean delete(@RequestBody List<Integer> ids) {
        return service.delete(ids);
    }

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @Override
    @PutMapping
    @PreAuthorize("@auth.hasAuth('sys:role:update')")
    @ApiOperation(value = "修改", notes = "创建时间，修改时间无需提交")
    public Boolean update(@RequestBody SysRole entity) {
        return service.update(entity);
    }

    /**
     * Find k.
     *
     * @param id the id
     * @return the k
     */
    @Override
    @GetMapping("/{id}")
    @PreAuthorize("@auth.hasAuth('sys:role:select')")
    @ApiOperation(value = "查询一条")
    public SysRole find(@PathVariable("id") int id) {
        return (SysRole) service.find(id);
    }

    /**
     * Search paged result.
     *
     * @param pagedInput the paged input
     * @return the paged result
     */
    @Override
    @PostMapping("/paged")
    @ApiOperation(value = "分页查询")
    @PreAuthorize("@auth.hasAuth('sys:role:select')")
    public PagedResult<SysRole> paged(@RequestBody PagedInputT<SysRole> pagedInput) {
        return service.paged(pagedInput);
    }
}
