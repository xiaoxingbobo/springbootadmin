package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.entity.dto.PagedInputT;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.SysRoleAuthorityResult;
import com.xxbb.springbootapi.mapper.SysRoleAuthorityMapper;
import com.xxbb.springbootapi.service.impl.SysRoleAuthorityService;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("RoleAuthoritys")
@Api(tags = "RoleAuthority表")
@RestController
public class RoleAuthorityController extends AuthApiController<SysRoleAuthority, SysRoleAuthorityQuery, SysRoleAuthorityUpdate, SysRoleAuthorityMapper> {


    @Autowired
    private SysRoleAuthorityService service;

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @Override
    @PostMapping
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:add')")
    @ApiOperation(value = "添加", notes = "id，创建时间，修改时间无需提交")
    public Boolean create(@RequestBody SysRoleAuthority entity) {
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
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:delete')")
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
    @DeleteMapping("/batch")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:delete')")
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
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:update')")
    @ApiOperation(value = "修改", notes = "创建时间，修改时间无需提交")
    public Boolean update(@RequestBody SysRoleAuthority entity) {
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
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    @ApiOperation(value = "查询一条")
    public SysRoleAuthority find(@PathVariable("id") int id) {
        return (SysRoleAuthority) service.find(id);
    }

    /**
     * Select list.
     *
     * @return the list
     */
    @Override
    @GetMapping
    @ApiOperation(value = "查询所有")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    public List<SysRoleAuthority> select() {
        return service.list();
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
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    public PagedResult<SysRoleAuthority> paged(@RequestBody PagedInputT<SysRoleAuthority> pagedInput) {
        return service.paged(pagedInput);
    }

    /**
     * 连接查询
     *
     * @param entity
     * @return
     */
    @PostMapping("/listJoin")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    @ApiOperation(value = "连接查询列表", notes = "id，创建时间，修改时间无需提交")
    public List<SysRoleAuthorityResult> listJoin(@RequestBody SysRoleAuthority entity) {
        return service.listJoin(entity);
    }

    /**
     * Create boolean.
     *
     * @param entities the entity
     * @return the boolean
     */
    @PutMapping("batch")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:update')")
    @ApiOperation(value = "批量修改", notes = "id，创建时间，修改时间无需提交")
    public Boolean updateBatch(@RequestBody List<SysRoleAuthority> entities) {
        return service.updateBatch(entities);
    }
}
