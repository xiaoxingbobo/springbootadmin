package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.entity.dto.MenuDTO;
import com.xxbb.springbootapi.entity.dto.PagedInputT;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.mapper.SysAuthorityMapper;
import com.xxbb.springbootapi.service.impl.SysAuthorityService;
import com.xxbb.springbootapi.wrapper.SysAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysAuthorityUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("authority")
@Api(tags = "Authority表")
@RestController
public class AuthorityController extends AuthApiController<SysAuthority, SysAuthorityQuery, SysAuthorityUpdate, SysAuthorityMapper> {
    @Autowired
    private SysAuthorityService service;

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @Override
    @PostMapping
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    @ApiOperation(value = "添加", notes = "id，创建时间，修改时间无需提交")
    public Boolean create(@RequestBody SysAuthority entity) {
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
    @PreAuthorize("@auth.hasAuth('sys:authority:delete')")
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
    @PreAuthorize("@auth.hasAuth('sys:authority:delete')")
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
    @PreAuthorize("@auth.hasAuth('sys:authority:update')")
    @ApiOperation(value = "修改", notes = "创建时间，修改时间无需提交")
    public Boolean update(@RequestBody SysAuthority entity) {
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
    @PreAuthorize("@auth.hasAuth('sys:authority:select')")
    @ApiOperation(value = "查询一条")
    public SysAuthority find(@PathVariable("id") int id) {
        return (SysAuthority) service.find(id);
    }

    /**
     * Select list.
     *
     * @return the list
     */
    @Override
    @GetMapping
    @ApiOperation(value = "查询所有")
    @PreAuthorize("@auth.hasAuth('sys:authority:select')")
    public List<SysAuthority> select() {
        return service.list();
    }


    /**
     * Search paged result.
     *
     * @param input the paged input
     * @return the paged result
     */
    @Override
    @PostMapping("/paged")
    @ApiOperation(value = "分页查询")
    @PreAuthorize("@auth.hasAuth('sys:authority:select')")
    public PagedResult<SysAuthority> paged(@RequestBody PagedInputT<SysAuthority> input) {
        return service.paged(input);
    }

    @GetMapping("/menu")
    @ApiOperation(value = "获取菜单")
    @ResponseBody
    public List<MenuDTO> viewMenu() {
        return service.viewMenu();
    }
}
