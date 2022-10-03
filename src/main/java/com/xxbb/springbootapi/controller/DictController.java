package com.xxbb.springbootapi.controller;


import com.xxbb.springbootapi.entity.dto.DictResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("dict")
@Api(tags = "字典接口")
@RestController
public class DictController {
    //字典列表
    @GetMapping("list")
    @ApiOperation(value = "字典列表")
    @ResponseBody
    public List<DictResult> captcha(String server, String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DictResult> dictResults = new ArrayList<>();
        dictResults.add(new DictResult().setValue(0).setLabel("tableDemo.commonly"));
        dictResults.add(new DictResult().setValue(1).setLabel("tableDemo.good"));
        dictResults.add(new DictResult().setValue(2).setLabel("tableDemo.important"));
        return dictResults;
    }
}
