package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IPeoperDao;
import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.mapper.PeoperMapper;
import com.xxbb.springbootapi.wrapper.PeoperQuery;
import com.xxbb.springbootapi.wrapper.PeoperUpdate;
import org.springframework.stereotype.Service;

@Service
public class PeoperDao extends BaseDao<Peoper, PeoperQuery, PeoperUpdate, PeoperMapper> implements IPeoperDao {

}
