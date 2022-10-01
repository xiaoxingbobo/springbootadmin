package com.xxbb.springbootapi.controller;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.entity.DataList;
import com.xxbb.springbootapi.service.impl.BaseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ApiController<K extends Common, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> extends BaseController {
    @Autowired(required = false)
    private BaseService<K, T, V, E> service;

    @PostMapping
    @ApiOperation(value = "添加", notes = "id，创建时间，修改时间无需提交")
    public Boolean add(@RequestBody K entity) {
        return service.add(entity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public Boolean delete(@PathVariable("id") int id) {
        return service.delete(id);
    }

    @PutMapping
    @ApiOperation(value = "修改", notes = "创建时间，修改时间无需提交")
    public Boolean update(@RequestBody K entity) {
        return service.update(entity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询一个")
    public K find(@PathVariable("id") int id) {
        return (K) service.find(id);
    }

    @GetMapping("/list/{limit}")
    @ApiOperation(value = "查询固定条数")
    @ApiImplicitParam(name = "limit", value = "限定条数", defaultValue = "10")
    public List<K> list(@PathVariable("limit") int limit) {
        return service.list(limit);
    }

    @GetMapping
    @ApiOperation(value = "查询所有")
    public List<K> list() {
        return service.list();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/{index}/{limit}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "数据索引", defaultValue = "0"),
            @ApiImplicitParam(name = "limit", value = "限定条数", defaultValue = "10")
    })
    public DataList<K> pagination(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return service.pagination(index, limit);
    }
}
