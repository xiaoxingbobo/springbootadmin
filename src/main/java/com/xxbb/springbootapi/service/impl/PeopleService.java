package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.mapper.PeopleMapper;
import com.xxbb.springbootapi.service.IPeopleService;
import com.xxbb.springbootapi.wrapper.PeopleQuery;
import com.xxbb.springbootapi.wrapper.PeopleUpdate;
import org.springframework.stereotype.Service;

@Service
public class PeopleService extends BaseService<People, PeopleQuery, PeopleUpdate, PeopleMapper> implements IPeopleService {

}

