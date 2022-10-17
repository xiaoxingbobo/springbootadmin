package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.mapper.DoopMapper;
import com.xxbb.springbootapi.wrapper.DoopQuery;
import com.xxbb.springbootapi.wrapper.DoopUpdate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("doop")
@Api(tags = "Doop表")
@RestController
public class DoopController extends ApiController<Doop, DoopQuery, DoopUpdate, DoopMapper> {

}
