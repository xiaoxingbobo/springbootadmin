package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.*;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.AccountMapper;
import com.xxbb.springbootapi.service.IAccountService;
import com.xxbb.springbootapi.util.Pbkdf2Util;
import com.xxbb.springbootapi.util.jwt.JwtUtil;
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountService extends BaseService<Account, AccountQuery, AccountUpdate, AccountMapper> implements IAccountService {

    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserService userService;
    @Autowired
    private SiteOperatorService siteOperatorService;

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public JsonResult Login(String account, String password) {
        Account obj = mapper().findOne(mapper().query().where.account().eq(account).end());
        if (obj == null) {
            return new JsonResult().Unknown("帐号不存在");
        } else if (!Pbkdf2Util.verify(obj.getPassword(), password)) {
            return new JsonResult().Fail("用户名或密码错误");
        }
        Map map = new HashMap();
        //获取用户权限
        //1.获取当前角色拥有的权限
        List<RoleAuthority> roleAuthorities = roleAuthorityService.dao.mapper.listEntity(roleAuthorityService.dao.mapper.query().where().roleId().eq(obj.getRoleId()).end());
        //2.获取当前角色拥有的权限的id列表
        List<Integer> roleAuthorityIdList = roleAuthorities.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        //3.查询包含在权限的id列表里面的权限,获取权限列表
        List<Authority> authorities = authorityService.mapper().listEntity(authorityService.mapper().query().where().id().in(roleAuthorityIdList).end());
        //4.获取权限列表里面的权限值，获取列表
        List<String> authorityList = authorities.stream().map(Authority::getValue).collect(Collectors.toList());
        //获取用户信息
        User user = userService.mapper().findById(obj.getUserId());
        //生成token
        Map<String, String> payload = new HashMap<>();
        payload.put("id", obj.getId().toString());//用户id
        payload.put("account", obj.getAccount());//用户账号
        payload.put("authority", authorityList.toString());//用户权限
        payload.put("userId", obj.getUserId().toString());//用户Id
        payload.put("name", user.getName());//用户账号
        String token = JwtUtil.getToken(payload, 60 * 60 * 12);
        return new JsonResultData<>(token).Success();
    }

    @Override
    public boolean add(Account entity) {
        entity.setId(null);//置空用户上传的id
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setIsDeleted(0);
        entity.setPassword(Pbkdf2Util.encryption(entity.getPassword()));//加密密码
        //判断账号是否存在
        Account account = mapper().findOne(mapper().query().where.account().eq(entity.getAccount()).end());
        if (account != null) {
            throw new LegalException("账号已存在");
        }
        return dao.add(entity) > 0;
    }

    @Override
    public boolean delete(int id) {
        if (id==1){
            throw new  LegalException("超级管理员为系统内置账号，不可删除");
        }else if (id==siteOperatorService.getOperator().getId()){
            throw new  LegalException("当前登录账号为你自己的账号，不可删除");
        }
        return super.delete(id);
    }
}
