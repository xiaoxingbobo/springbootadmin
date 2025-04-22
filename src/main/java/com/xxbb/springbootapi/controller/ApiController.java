package com.xxbb.springbootapi.controller;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.entity.SysCommon;
import com.xxbb.springbootapi.entity.dto.PagedInputT;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.service.impl.SysBaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Api controller.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 * @param <V> the type parameter
 * @param <E> the type parameter
 * @author xiaoxingbobo
 */
public class ApiController<K extends SysCommon, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> {
    @Autowired(required = false)
    private SysBaseService<K, T, V, E> service;


    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    @PostMapping
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
    @ApiOperation(value = "删除")
    public Boolean delete(@PathVariable("id") int id) {
        return service.delete(id);
    }

    @DeleteMapping("/batch")
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
    @ApiOperation(value = "修改", notes = "创建时间，修改时间无需提交")
    public Boolean update(@RequestBody K entity) {
        return service.update(entity);
    }


    /**
     * Find k.
     *
     * @param id the id
     * @return the k
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询一条")
    public K find(@PathVariable("id") int id) {
        return (K) service.find(id);
    }

    /**
     * Search paged result.
     *
     * @param input the paged input
     * @return the paged result
     */
    @PostMapping("/paged/condition")
    @ApiOperation(value = "分页搜索")
    public PagedResult<K> paged(@RequestBody PagedInputT<K> input) {
        return service.paged(input);
    }
}
