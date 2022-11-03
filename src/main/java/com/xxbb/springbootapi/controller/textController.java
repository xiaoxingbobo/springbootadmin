package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.text;
import com.xxbb.springbootapi.mapper.textMapper;
import com.xxbb.springbootapi.wrapper.textQuery;
import com.xxbb.springbootapi.wrapper.textUpdate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("text")
@Api(tags = "text表")
@RestController
public class textController extends ApiController<text, textQuery, textUpdate, textMapper> {

}
