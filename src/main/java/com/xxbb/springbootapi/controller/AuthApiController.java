package com.xxbb.springbootapi.controller;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.entity.SysCommon;
import com.xxbb.springbootapi.entity.dto.PagedInput;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;
import com.xxbb.springbootapi.service.impl.SysBaseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Auth api controller.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 * @param <V> the type parameter
 * @param <E> the type parameter
 */
public class AuthApiController<K extends SysCommon, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> {
    @Autowired(required = false)
    private SysBaseService<K, T, V, E> service;


    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @PostMapping
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    @ApiOperation(value = "添加", notes = "id，创建时间，修改时间无需提交")
    public Boolean create(@RequestBody K entity) {
        return service.add(entity);
    }

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
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
    @DeleteMapping("/batch")
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
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
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    @ApiOperation(value = "修改", notes = "创建时间，修改时间无需提交")
    public Boolean update(@RequestBody K entity) {
        return service.update(entity);
    }

    /**
     * Select list.
     *
     * @param searches the searches
     * @return the list
     */
    @PostMapping("/searches")
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    @ApiOperation(value = "搜索")
    public List<K> select(@RequestBody List<Search> searches) {
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
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    @ApiOperation(value = "查询一条")
    public K find(@PathVariable("id") int id) {
        return (K) service.find(id);
    }

    /**
     * Select list.
     *
     * @param limit the limit
     * @return the list
     */
    @GetMapping("/list/{limit}")
    @ApiOperation(value = "查询固定条数")
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    @ApiImplicitParam(name = "limit", value = "限定条数", defaultValue = "10")
    public List<K> select(@PathVariable("limit") int limit) {
        return service.list(limit);
    }

    /**
     * Select list.
     *
     * @return the list
     */
    @GetMapping
    @ApiOperation(value = "查询所有")
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    public List<K> select() {
        return service.list();
    }

    /**
     * Select paged result.
     *
     * @param input the input
     * @return the paged result
     */
    @PostMapping("/paged")
    @ApiOperation(value = "分页筛选")
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    public PagedResult<K> select(@RequestBody PagedInputC<K> input) {
        return service.list(input);
    }

    /**
     * Paged paged result.
     *
     * @param current the current
     * @param size    the size
     * @return the paged result
     */
    @ApiOperation(value = "分页查询")
    @GetMapping("/paged/{current}/{size}")
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"), @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "10")})
    public PagedResult<K> paged(@PathVariable("current") int current, @PathVariable("size") int size) {
        return service.paged(new PagedInput().setCurrent(current).setSize(size));
    }

    /**
     * Search paged result.
     *
     * @param pagedInput the paged input
     * @return the paged result
     */
    @PostMapping("/paged/searches")
    @ApiOperation(value = "分页搜索")
    @PreAuthorize("@auth.hasAuth('sys:authority:add')")
    public PagedResult<K> search(@RequestBody PagedInputC<List<Search>> pagedInput) {
        return service.searchPaged(pagedInput);
    }
}
