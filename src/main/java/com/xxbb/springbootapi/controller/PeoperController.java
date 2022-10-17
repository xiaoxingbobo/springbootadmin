package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.mapper.PeoperMapper;
import com.xxbb.springbootapi.wrapper.PeoperQuery;
import com.xxbb.springbootapi.wrapper.PeoperUpdate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("peoper")
@Api(tags = "Peoper表")
@RestController
public class PeoperController extends ApiController<Peoper, PeoperQuery, PeoperUpdate, PeoperMapper> {

}
