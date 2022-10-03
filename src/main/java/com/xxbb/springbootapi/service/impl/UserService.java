package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.config.AppConfig;
import com.xxbb.springbootapi.entity.*;
import com.xxbb.springbootapi.entity.dto.LoginInput;
import com.xxbb.springbootapi.entity.dto.LoginResult;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.service.IUserService;
import com.xxbb.springbootapi.util.Pbkdf2Util;
import com.xxbb.springbootapi.util.jwt.JwtUtil;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<User, UserQuery, UserUpdate, UserMapper> implements IUserService {
    @Autowired
    private SiteOperatorService siteOperatorService;
    @Autowired
    private AppConfig appConfig;

    @Qualifier("fmUserMapper")
    @Autowired(required = false)
    UserMapper mapper;
    @Autowired(required = false)
    UserQuery query;

    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private AuthorityService authorityService;


    @Override
    public User getSiteOperator() {
        Integer userId = siteOperatorService.getOperator().getUserId();
        return mapper().findById(userId);
    }

    @Override
    public JsonResult login(LoginInput input) {
        User user = mapper.findOne(mapper().query().where().username().eq(input.getUsername()).end());
        if (user == null) {
            return new JsonResult().Null("账号不存在");
        } else if (!Pbkdf2Util.verify(user.getPassword(), input.getPassword())) {
            return new JsonResult().Fail("账号或者密码错误");
        }
        //获取用户权限
        //1.获取当前角色拥有的权限
        List<RoleAuthority> roleAuthorities = roleAuthorityService.dao.mapper.listEntity(roleAuthorityService.dao.mapper.query().where().roleId().eq(user.getRoleId()).end());
        //2.获取当前角色拥有的权限的id列表
        List<Integer> roleAuthorityIdList = roleAuthorities.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        //3.查询包含在权限的id列表里面的权限,获取权限列表
        List<Authority> authorities = authorityService.mapper().listEntity(authorityService.mapper().query().where().id().in(roleAuthorityIdList).end());
        //4.获取权限列表里面的权限值，获取列表
        List<String> authorityList = authorities.stream().map(Authority::getValue).collect(Collectors.toList());
        //生成token
        Map<String, String> payload = new HashMap<>();
        payload.put("id", user.getId().toString());//用户id
        payload.put("account", user.getUsername());//用户账号
        payload.put("authority", authorityList.toString());//用户权限
        payload.put("name", user.getName());//用户账号
        String token = JwtUtil.getToken(payload, 60 * 60 * 12);
        user.setPassword("想啥呢，这里不给你看");
        return new JsonResultData<>().Success(new LoginResult().setToken(token).setUserInfo(user).setCaptcha(false).setRoutePath(appConfig.getAdminIndex()), "登录成功");
    }

    @Override
    public boolean add(User entity) {
        entity.setId(null);//置空用户上传的id
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setIsDeleted(0);
        entity.setPassword(Pbkdf2Util.encryption(entity.getPassword()));//加密密码
        //判断账号是否存在
        User user = mapper().findOne(mapper().query().where.username().eq(entity.getUsername()).end());
        if (user != null) {
            throw new LegalException("账号已存在");
        }
        return dao.add(entity) > 0;
    }

    //删除
    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员为系统内置账号，不可删除");
        } else if (id == siteOperatorService.getOperator().getId()) {
            throw new LegalException("当前登录账号为你自己的账号，不可删除");
        }
        return super.delete(id);
    }
}
