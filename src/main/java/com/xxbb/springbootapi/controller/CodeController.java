package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.service.impl.CodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
