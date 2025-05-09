package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.SysUser;
import com.xxbb.springbootapi.entity.dto.*;
import com.xxbb.springbootapi.mapper.SysUserMapper;
import com.xxbb.springbootapi.service.impl.SysUserService;
import com.xxbb.springbootapi.wrapper.SysUserQuery;
import com.xxbb.springbootapi.wrapper.SysUserUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@Api(tags = "User表")
@RestController
public class UserController extends AuthApiController<SysUser, SysUserQuery, SysUserUpdate, SysUserMapper> {
    @Autowired
    private SysUserService service;

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @PostMapping
    @PreAuthorize("@auth.hasAuth('sys:user:add')")
    @ApiOperation(value = "添加", notes = "id，创建时间，修改时间无需提交")
    public Boolean create(@RequestBody SysUser entity) {
        return super.create(entity);
    }

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@auth.hasAuth('sys:user:delete')")
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
    @PostMapping("/delete")
    @PreAuthorize("@auth.hasAuth('sys:user:delete')")
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
    @PutMapping
    @PreAuthorize("@auth.hasAuth('sys:user:update')")
    @ApiOperation(value = "修改", notes = "创建时间，修改时间无需提交")
    public Boolean update(@RequestBody SysUser entity) {
        return service.update(entity);
    }

    /**
     * Select list.
     *
     * @param searches the searches
     * @return the list
     */
    @PostMapping("/searches")
    @PreAuthorize("@auth.hasAuth('sys:user:select')")
    @ApiOperation(value = "搜索")
    public List<SysUser> select(@RequestBody List<Search> searches) {
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
    @GetMapping("/{id}")
    @PreAuthorize("@auth.hasAuth('sys:user:select')")
    @ApiOperation(value = "查询一条")
    public SysUser find(@PathVariable("id") int id) {
        return (SysUser) service.find(id).setPassword(null);
    }

    /**
     * Select list.
     *
     * @param limit the limit
     * @return the list
     */
    @GetMapping("/list/{limit}")
    @ApiOperation(value = "查询固定条数")
    @PreAuthorize("@auth.hasAuth('sys:user:select')")
    @ApiImplicitParam(name = "limit", value = "限定条数", defaultValue = "10")
    public List<SysUser> select(@PathVariable("limit") int limit) {
        return service.list(limit);
    }

    /**
     * Select list.
     *
     * @return the list
     */
    @GetMapping
    @ApiOperation(value = "查询所有")
    @PreAuthorize("@auth.hasAuth('sys:user:select')")
    public List<SysUser> select() {
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
    @PreAuthorize("@auth.hasAuth('sys:user:select')")
    public PagedResult<SysUser> paged(@RequestBody PagedInputT<SysUser> pagedInput) {
        return service.paged(pagedInput);
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public JsonResultData<LoginResult> login(@RequestBody LoginInput input) {
        return service.login(input);
    }

    @GetMapping("logout")
    @ApiOperation(value = "注销")
    public JsonResult logout() {
        return new JsonResult().Success();
    }
}