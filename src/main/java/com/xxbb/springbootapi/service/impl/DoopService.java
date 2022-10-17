package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.mapper.DoopMapper;
import com.xxbb.springbootapi.service.IDoopService;
import com.xxbb.springbootapi.wrapper.DoopQuery;
import com.xxbb.springbootapi.wrapper.DoopUpdate;
import org.springframework.stereotype.Service;

@Service
public class DoopService extends BaseService<Doop, DoopQuery, DoopUpdate, DoopMapper> implements IDoopService {

}

