package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Dds;
import com.xxbb.springbootapi.mapper.DdsMapper;
import com.xxbb.springbootapi.wrapper.DdsQuery;
import com.xxbb.springbootapi.wrapper.DdsUpdate;

public interface IDdsService extends IBaseService<Dds, DdsQuery, DdsUpdate, DdsMapper> {

}
