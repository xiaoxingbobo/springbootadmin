package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.SysGenRecord;
import com.xxbb.springbootapi.entity.dto.CodeInput;
import com.xxbb.springbootapi.service.impl.SysCodeService;
import com.xxbb.springbootapi.utils.ClassUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("code")
@Api(tags = "代码生成")
@RestController
public class CodeController {
    @Autowired(required = false)
    private SysCodeService codeService;

    /**
     * 代码生成
     *
     * @param entityName
     * @param isCover
     * @return
     */
    @PostMapping("generate")
    @PreAuthorize("@auth.hasAuth('sys:code:select')")
    @ApiOperation(value = "接口代码生成")
    public Boolean Generate(@RequestBody CodeInput codeInput) {
        return codeService.generate(codeInput);
    }

    @GetMapping("classes")
    @ApiOperation(value = "可生成实体列表")
    @PreAuthorize("@auth.hasAuth('sys:code:select')")
    public List<String> getClasses() {
        List<String> classes = new ArrayList<>();
        for (Class<?> item : ClassUtil.getClasses("com.xxbb.springbootapi.entity", false)) {//false不迭代，不获取子类
            classes.add(item.getName().substring(item.getName().lastIndexOf('.') + 1));
        }
        return classes;
    }

    @GetMapping("undo/{id}")
    @ApiOperation(value = "撤销生成")
    @PreAuthorize("@auth.hasAuth('sys:code:undo')")
    public boolean undoGen(@PathVariable("id") Integer id) {
        return codeService.delete(id);
    }

    @GetMapping("list")
    @PreAuthorize("@auth.hasAuth('sys:code:select')")
    @ApiOperation(value = "所有生成记录")
    public List<SysGenRecord> list() {
        return codeService.list();
    }
}
