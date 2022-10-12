package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.service.ICodeGenerate;
import com.xxbb.springbootapi.utils.code.CodeGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 代码生成
 */
@Service
public class CodeService implements ICodeGenerate {
    @Autowired
    private GenRecordService genRecordService;

    @Override
    public Boolean generate(String entityName, Boolean isCover) {
        genRecordService.add(new GenRecord().setEntityName(entityName));
        return CodeGen.build(entityName, isCover);//代码生成，参数：要生成代码的实体类名
    }

    @Override
    public Boolean delete(Integer id) {
        GenRecord genRecord = genRecordService.find(id);
        genRecordService.delete(genRecord.getId());
        return CodeGen.delete(genRecord.getEntityName());//代码删除，参数：要删除代码的实体类名
    }

    @Override
    public List<GenRecord> list() {
        return genRecordService.list();
    }

}
