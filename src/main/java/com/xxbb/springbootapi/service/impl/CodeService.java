package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.service.ICodeGenerate;
import com.xxbb.springbootapi.utils.code.Generation;
import org.springframework.stereotype.Service;

/**
 * 代码生成
 */
@Service
public class CodeService implements ICodeGenerate {
    @Override
    public Boolean Generate(String entityName, Boolean isCover) {
       return Generation.build(entityName,isCover);//代码生成，参数：要生成代码的实体类名
    }
}
