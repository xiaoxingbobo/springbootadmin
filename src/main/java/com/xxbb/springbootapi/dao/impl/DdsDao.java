package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IDdsDao;
import com.xxbb.springbootapi.entity.Dds;
import com.xxbb.springbootapi.mapper.DdsMapper;
import com.xxbb.springbootapi.wrapper.DdsQuery;
import com.xxbb.springbootapi.wrapper.DdsUpdate;
import org.springframework.stereotype.Service;

@Service
public class DdsDao extends BaseDao<Dds, DdsQuery, DdsUpdate, DdsMapper> implements IDdsDao {

}
