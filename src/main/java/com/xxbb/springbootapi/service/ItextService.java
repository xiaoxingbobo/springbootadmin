package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.text;
import com.xxbb.springbootapi.mapper.textMapper;
import com.xxbb.springbootapi.wrapper.textQuery;
import com.xxbb.springbootapi.wrapper.textUpdate;

public interface ItextService extends IBaseService<text, textQuery, textUpdate, textMapper> {

}
