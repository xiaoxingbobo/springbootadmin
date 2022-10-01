package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("Authoritys")
@Api(tags = "Authority表")
@RestController
public class AuthorityController extends ApiController<Authority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> {

}
