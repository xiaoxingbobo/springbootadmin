package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.service.IBaseService;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService extends BaseService<Authority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> implements IBaseService<Authority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> {

}
