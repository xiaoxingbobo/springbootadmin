package com.xxbb.springbootapi.controller;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.entity.dto.PagedInput;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;
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
    public Boolean create(@RequestBody K entity) {
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

    @PostMapping("/searches")
    @ApiOperation(value = "搜索")
    public List<K> select(@RequestBody List<Search> searches) {
        if(searches.size()==1)
            return service.search(searches.get(0));
        else if (searches.size()>1)
            return service.search(searches);
        else
            return service.list();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询一条")
    public K find(@PathVariable("id") int id) {
        return (K) service.find(id);
    }

    @GetMapping("/list/{limit}")
    @ApiOperation(value = "查询固定条数")
    @ApiImplicitParam(name = "limit", value = "限定条数", defaultValue = "10")
    public List<K> select(@PathVariable("limit") int limit) {
        return service.list(limit);
    }

    @GetMapping
    @ApiOperation(value = "查询所有")
    public List<K> select() {
        return service.list();
    }

    @PostMapping("/paged")
    @ApiOperation(value = "分页筛选")
    public PagedResult<K> select(@RequestBody PagedInputC<K> input) {
        return service.list(input);
    }
    @ApiOperation(value = "分页查询")
    @GetMapping("/paged/{current}/{size}")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"), @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "10")})
    public PagedResult<K> paged(@PathVariable("current") int current, @PathVariable("size") int size) {
        return service.paged(new PagedInput().setCurrent(current).setSize(size));
    }
    @PostMapping("/paged/searches")
    @ApiOperation(value = "分页搜索")
    public PagedResult<K> search(@RequestBody PagedInputC<List<Search>> pagedInput) {
        return service.searchPaged(pagedInput);
    }
}
