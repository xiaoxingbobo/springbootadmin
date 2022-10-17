package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.mapper.PeopleMapper;
import com.xxbb.springbootapi.wrapper.PeopleQuery;
import com.xxbb.springbootapi.wrapper.PeopleUpdate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("people")
@Api(tags = "People表")
@RestController
public class PeopleController extends ApiController<People, PeopleQuery, PeopleUpdate, PeopleMapper> {

}
