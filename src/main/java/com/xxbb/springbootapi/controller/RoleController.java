package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("Roles")
@Api(tags = "Role表")
@RestController
public class RoleController extends AuthApiController<Role, RoleQuery, RoleUpdate, RoleMapper> {

}
