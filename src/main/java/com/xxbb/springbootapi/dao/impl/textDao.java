package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.ItextDao;
import com.xxbb.springbootapi.entity.text;
import com.xxbb.springbootapi.mapper.textMapper;
import com.xxbb.springbootapi.wrapper.textQuery;
import com.xxbb.springbootapi.wrapper.textUpdate;
import org.springframework.stereotype.Service;

@Service
public class textDao extends BaseDao<text, textQuery, textUpdate, textMapper> implements ItextDao {

}
