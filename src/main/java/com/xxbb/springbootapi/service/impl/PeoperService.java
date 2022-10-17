package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.mapper.PeoperMapper;
import com.xxbb.springbootapi.service.IPeoperService;
import com.xxbb.springbootapi.wrapper.PeoperQuery;
import com.xxbb.springbootapi.wrapper.PeoperUpdate;
import org.springframework.stereotype.Service;

@Service
public class PeoperService extends BaseService<Peoper, PeoperQuery, PeoperUpdate, PeoperMapper> implements IPeoperService {

}

