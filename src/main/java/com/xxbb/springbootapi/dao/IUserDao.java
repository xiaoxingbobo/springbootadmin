package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao extends IBaseDao<User, UserQuery, UserUpdate, UserMapper> {

}
