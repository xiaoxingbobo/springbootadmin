package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("test")
@Api(tags = "Test表")
@RestController
public class TestController extends ApiController<Test, TestQuery, TestUpdate, TestMapper> {

    @Override
    public Boolean create(@RequestBody Test entity) {
        return super.create(entity);
    }

    @Override
    public Boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public Boolean update(Test entity) {
        return super.update(entity);
    }

    @Override
    public List<Test> select(List<Search> searches) {
        return super.select(searches);
    }

    @Override
    public Test find(int id) {
        return super.find(id);
    }

    @Override
    public List<Test> select(int limit) {
        return super.select(limit);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:test:select')")
    public List<Test> select() {
        return super.select();
    }

    @Override
    public PagedResult<Test> select(PagedInputC<Test> input) {
        return super.select(input);
    }

    @Override
    public PagedResult<Test> paged(int current, int size) {
        return super.paged(current, size);
    }

    @Override
    public PagedResult<Test> search(PagedInputC<List<Search>> pagedInput) {
        return super.search(pagedInput);
    }
}
