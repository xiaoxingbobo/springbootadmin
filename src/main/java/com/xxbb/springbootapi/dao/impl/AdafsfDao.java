package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IAdafsfDao;
import com.xxbb.springbootapi.entity.Adafsf;
import com.xxbb.springbootapi.mapper.AdafsfMapper;
import com.xxbb.springbootapi.wrapper.AdafsfQuery;
import com.xxbb.springbootapi.wrapper.AdafsfUpdate;
import org.springframework.stereotype.Service;

@Service
public class AdafsfDao extends BaseDao<Adafsf, AdafsfQuery, AdafsfUpdate, AdafsfMapper> implements IAdafsfDao {

}
