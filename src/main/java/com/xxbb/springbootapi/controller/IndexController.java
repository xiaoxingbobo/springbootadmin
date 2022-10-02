package com.xxbb.springbootapi.controller;


import com.xxbb.springbootapi.entity.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("index")
@Api(tags = "index首页")
@RestController
public class IndexController {
    @GetMapping("index")
    @ApiOperation(value = "首页")
    public JsonResult index() {
        return new JsonResult().Success();
    }
}
