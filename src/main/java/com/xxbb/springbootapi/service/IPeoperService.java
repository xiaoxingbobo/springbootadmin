package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.mapper.PeoperMapper;
import com.xxbb.springbootapi.wrapper.PeoperQuery;
import com.xxbb.springbootapi.wrapper.PeoperUpdate;

public interface IPeoperService extends IBaseService<Peoper, PeoperQuery, PeoperUpdate, PeoperMapper> {

}
