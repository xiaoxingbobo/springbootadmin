package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.GenRecord;

import java.util.List;

public interface ICodeGenerate {
    Boolean generate(String entityName,Boolean isCover);

    Boolean delete(Integer id);

    List<GenRecord> list();
}
