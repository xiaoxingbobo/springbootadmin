package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.dao.impl.TestDao;
import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.entity.dto.Search;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("Tests")
@Api(tags = "Test表")
@RestController
public class TestController extends ApiController<Test, TestQuery, TestUpdate, TestMapper> {

    @Autowired
    private TestDao dao;

    @GetMapping("search")
    @ApiOperation(value = "搜索")
    public List<Test> search(Search search) {
//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
        return dao.search(search);
    }
}
