package com.xxbb.springbootapi.service.impl;

import com.auth0.jwt.JWTCreator;
import com.xxbb.springbootapi.config.AppConfig;
import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.entity.dto.JsonResult;
import com.xxbb.springbootapi.entity.dto.JsonResultData;
import com.xxbb.springbootapi.entity.dto.LoginInput;
import com.xxbb.springbootapi.entity.dto.LoginResult;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.service.IUserService;
import com.xxbb.springbootapi.utils.jwt.JwtUtil;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<User, UserQuery, UserUpdate, UserMapper> implements IUserService {
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
    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 获取当前登录者信息
     *
     * @return
     */
    @Override
    public User getUserInfo() {
        return mapper().findById(getUser().getId());
    }


    @Override
    public JsonResult login(LoginInput input) {

        //AuthenticationManager 进行身份验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new LegalException("用户名或密码错误");
        }

        //获取用户信息
        SecurityUserDetails userDetails = (SecurityUserDetails) (authenticate.getPrincipal());

        User user = userDetails.getUser();
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
        JWTCreator.Builder builder = JwtUtil.createBuilder();
        builder.withClaim("id", user.getId());//用户id
        builder.withClaim("username", user.getUsername());//用户账号
        builder.withClaim("authority", authorityList);//用户权限
        builder.withClaim("name", user.getName());//用户姓名
        builder.withClaim("roleId", user.getRoleId());//用户角色id
        String token = JwtUtil.getToken(builder, 60 * 60 * 12);
        user.setPassword("想啥呢，这里不给你看");
        return new JsonResultData<>().Success(new LoginResult().setToken(token).setUserInfo(user).setCaptcha(false).setRoutePath(appConfig.getAdminIndex()), "登录成功");
    }

    /**
     * 注销
     *
     * @param
     * @return
     */
    @Override
    public JsonResult logout() {
        return new JsonResultData<>().Success("注销成功");
    }

    /**
     * 添加
     * @param entity
     * @return
     */
    @Override
    public boolean add(User entity) {
        entity.setId(null);//置空用户上传的id
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setIsDeleted(0);
//        entity.setPassword(Pbkdf2Util.encryption(entity.getPassword()));//加密密码
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));//加密密码
        //判断账号是否存在
        User user = mapper().findOne(mapper().query().where.username().eq(entity.getUsername()).end());
        if (user != null) {
            throw new LegalException("账号已存在");
        }
        return dao.insert(entity) > 0;
    }

    //删除
    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员为系统内置账号，不可删除");
        } else if (id == getUser().getId()) {
            throw new LegalException("当前登录账号为你自己的账号，不可删除");
        }
        return super.delete(id);
    }

    /**
     * 当前登录用户，只含有id，username，name，roleId
     *
     * @return
     */
    @Override
    public User getUser() {
        //从Security中获取用户信息
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
