package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.entity.dto.PagedInput;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.service.impl.AuthorityService;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("authority")
@Api(tags = "Authority表")
@RestController
public class AuthorityController extends AuthApiController<Authority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> {
    @Autowired
    private AuthorityService service;
    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @Override
    @PostMapping
    @PreAuthorize("hasAnyAuthority('sys:authority:add','sys:all:all')")
    @ApiOperation(value = "添加", notes = "id，创建时间，修改时间无需提交")
    public Boolean create(@RequestBody Authority entity) {
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
    @PreAuthorize("hasAnyAuthority('sys:authority:delete','sys:all:all')")
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
    @PreAuthorize("hasAnyAuthority('sys:authority:delete','sys:all:all')")
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
    @PreAuthorize("hasAnyAuthority('sys:authority:update','sys:all:all')")
    @ApiOperation(value = "修改", notes = "创建时间，修改时间无需提交")
    public Boolean update(@RequestBody Authority entity) {
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
    @PreAuthorize("hasAnyAuthority('sys:authority:select','sys:all:all')")
    @ApiOperation(value = "搜索")
    public List<Authority> select(@RequestBody List<Search> searches) {
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
    @PreAuthorize("hasAnyAuthority('sys:authority:select','sys:all:all')")
    @ApiOperation(value = "查询一条")
    public Authority find(@PathVariable("id") int id) {
        return (Authority) service.find(id);
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
    @PreAuthorize("hasAnyAuthority('sys:authority:select','sys:all:all')")
    @ApiImplicitParam(name = "limit", value = "限定条数", defaultValue = "10")
    public List<Authority> select(@PathVariable("limit") int limit) {
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
    @PreAuthorize("hasAnyAuthority('sys:authority:select','sys:all:all')")
    public List<Authority> select() {
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
    @PreAuthorize("hasAnyAuthority('sys:authority:select','sys:all:all')")
    public PagedResult<Authority> select(@RequestBody PagedInputC<Authority> input) {
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
    @PreAuthorize("hasAnyAuthority('sys:authority:select','sys:all:all')")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"), @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "10")})
    public PagedResult<Authority> paged(@PathVariable("current") int current, @PathVariable("size") int size) {
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
    @PreAuthorize("hasAnyAuthority('sys:authority:select','sys:all:all')")
    public PagedResult<Authority> search(@RequestBody PagedInputC<List<Search>> pagedInput) {
        return service.searchPaged(pagedInput);
    }
}
