package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IAuthorityDao;
import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import org.springframework.stereotype.Service;

@Service
public class AuthorityDao extends BaseDao<Authority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> implements IAuthorityDao {

}
