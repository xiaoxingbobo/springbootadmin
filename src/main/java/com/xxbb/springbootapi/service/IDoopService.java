package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.mapper.DoopMapper;
import com.xxbb.springbootapi.wrapper.DoopQuery;
import com.xxbb.springbootapi.wrapper.DoopUpdate;

public interface IDoopService extends IBaseService<Doop, DoopQuery, DoopUpdate, DoopMapper> {

}
