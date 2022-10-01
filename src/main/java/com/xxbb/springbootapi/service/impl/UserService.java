package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.service.IUserService;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserQuery, UserUpdate, UserMapper> implements IUserService {
    @Autowired
    private SiteOperatorService siteOperatorService;

    @Override
    public User getSiteOperator() {
        Integer userId = siteOperatorService.getOperator().getUserId();
        return mapper().findById(userId);
    }
}
