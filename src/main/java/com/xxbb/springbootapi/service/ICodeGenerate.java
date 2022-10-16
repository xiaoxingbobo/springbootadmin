package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.entity.dto.CodeInput;

import java.util.List;

public interface ICodeGenerate {
    Boolean generate(CodeInput codeInput);

    Boolean delete(Integer id);

    List<GenRecord> list();
}
