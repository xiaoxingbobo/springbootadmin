package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.mapper.PeopleMapper;
import com.xxbb.springbootapi.wrapper.PeopleQuery;
import com.xxbb.springbootapi.wrapper.PeopleUpdate;

public interface IPeopleService extends IBaseService<People, PeopleQuery, PeopleUpdate, PeopleMapper> {

}
