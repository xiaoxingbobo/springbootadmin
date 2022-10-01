package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAuthorityDao extends IBaseDao<Authority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> {

}
