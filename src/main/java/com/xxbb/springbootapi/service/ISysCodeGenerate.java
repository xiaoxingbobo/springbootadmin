package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.SysGenRecord;
import com.xxbb.springbootapi.entity.dto.CodeInput;

import java.util.List;

public interface ISysCodeGenerate {
    Boolean generate(CodeInput codeInput);

    Boolean delete(Integer id);

    List<SysGenRecord> list();
}
