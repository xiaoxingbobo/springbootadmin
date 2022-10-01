package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("Tests")
@Api(tags = "Test表")
@RestController
public class TestController extends ApiController<Test, TestQuery, TestUpdate, TestMapper> {

}
