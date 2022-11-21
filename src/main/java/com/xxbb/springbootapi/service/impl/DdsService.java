package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Dds;
import com.xxbb.springbootapi.mapper.DdsMapper;
import com.xxbb.springbootapi.service.IDdsService;
import com.xxbb.springbootapi.wrapper.DdsQuery;
import com.xxbb.springbootapi.wrapper.DdsUpdate;
import org.springframework.stereotype.Service;

@Service
public class DdsService extends BaseService<Dds, DdsQuery, DdsUpdate, DdsMapper> implements IDdsService {

}

