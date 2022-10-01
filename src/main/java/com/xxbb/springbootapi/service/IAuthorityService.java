package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;

public interface IAuthorityService extends IBaseService<Authority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> {

}
