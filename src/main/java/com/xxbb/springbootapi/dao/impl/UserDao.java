package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IUserDao;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import org.springframework.stereotype.Service;

@Service
public class UserDao extends BaseDao<User, UserQuery, UserUpdate, UserMapper> implements IUserDao {

}