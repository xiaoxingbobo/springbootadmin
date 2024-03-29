package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.entity.dto.*;
import com.xxbb.springbootapi.mapper.SysRoleAuthorityMapper;
import com.xxbb.springbootapi.service.impl.SysRoleAuthorityService;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysRoleAuthorityUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
     * Select list.
     *
     * @param searches the searches
     * @return the list
     */
    @Override
    @PostMapping("/searches")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    @ApiOperation(value = "搜索")
    public List<SysRoleAuthority> select(@RequestBody List<Search> searches) {
        if (searches.size() == 1) {
            return service.search(searches.get(0));
        } else if (searches.size() > 1) {
            return service.search(searches);
        } else {
            return service.list();
        }
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
     * @param limit the limit
     * @return the list
     */
    @Override
    @GetMapping("/list/{limit}")
    @ApiOperation(value = "查询固定条数")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    @ApiImplicitParam(name = "limit", value = "限定条数", defaultValue = "10")
    public List<SysRoleAuthority> select(@PathVariable("limit") int limit) {
        return service.list(limit);
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
     * Select paged result.
     *
     * @param input the input
     * @return the paged result
     */
    @Override
    @PostMapping("/paged")
    @ApiOperation(value = "分页筛选")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    public PagedResult<SysRoleAuthority> select(@RequestBody PagedInputC<SysRoleAuthority> input) {
        return service.list(input);
    }

    /**
     * Paged paged result.
     *
     * @param current the current
     * @param size    the size
     * @return the paged result
     */
    @Override
    @ApiOperation(value = "分页查询")
    @GetMapping("/paged/{current}/{size}")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"), @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "10")})
    public PagedResult<SysRoleAuthority> paged(@PathVariable("current") int current, @PathVariable("size") int size) {
        return service.paged(new PagedInput().setCurrent(current).setSize(size));
    }

    /**
     * Search paged result.
     *
     * @param pagedInput the paged input
     * @return the paged result
     */
    @Override
    @PostMapping("/paged/searches")
    @ApiOperation(value = "分页搜索")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:select')")
    public PagedResult<SysRoleAuthority> search(@RequestBody PagedInputC<List<Search>> pagedInput) {
        return service.searchPaged(pagedInput);
    }

    /**
     * 连接查询
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
     * @param entity the entity
     * @return the boolean
     */
    @PutMapping ("batch")
    @PreAuthorize("@auth.hasAuth('sys:roleAuthority:update')")
    @ApiOperation(value = "批量修改", notes = "id，创建时间，修改时间无需提交")
    public Boolean updateBatch(@RequestBody List<SysRoleAuthority> entities) {
        return service.updateBatch(entities);
    }
}
