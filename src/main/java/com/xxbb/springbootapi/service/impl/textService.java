package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.text;
import com.xxbb.springbootapi.mapper.textMapper;
import com.xxbb.springbootapi.service.ItextService;
import com.xxbb.springbootapi.wrapper.textQuery;
import com.xxbb.springbootapi.wrapper.textUpdate;
import org.springframework.stereotype.Service;

@Service
public class textService extends BaseService<text, textQuery, textUpdate, textMapper> implements ItextService {

}

