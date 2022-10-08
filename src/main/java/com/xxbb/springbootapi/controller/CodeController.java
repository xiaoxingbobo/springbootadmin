package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.service.impl.CodeService;
import com.xxbb.springbootapi.util.ClassUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("code")
@Api(tags = "代码生成")
@RestController
public class CodeController {
    @Autowired(required = false)
    private CodeService codeService;

    /**
     * 代码生成
     * @param entityName
     * @param isCover
     * @return
     */
    @PostMapping("generate")
    @ApiOperation(value = "单个实体生成")
    public Boolean Generate(String entityName,Boolean isCover){
        return codeService.Generate(entityName,isCover);
    }
    @GetMapping("classes")
    @ApiOperation(value = "获取所有实体类")
    public List<String> getClasses() {
        List<String> classes=new ArrayList<>();
        for (Class<?> item : ClassUtil.getClasses("com.xxbb.springbootapi.entity")) {
            classes.add(item.getName().substring(item.getName().lastIndexOf('.')+1));
        }
        return classes;
    }
}
