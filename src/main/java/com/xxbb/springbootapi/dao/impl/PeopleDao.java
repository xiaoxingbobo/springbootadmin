package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IPeopleDao;
import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.mapper.PeopleMapper;
import com.xxbb.springbootapi.wrapper.PeopleQuery;
import com.xxbb.springbootapi.wrapper.PeopleUpdate;
import org.springframework.stereotype.Service;

@Service
public class PeopleDao extends BaseDao<People, PeopleQuery, PeopleUpdate, PeopleMapper> implements IPeopleDao {

}
