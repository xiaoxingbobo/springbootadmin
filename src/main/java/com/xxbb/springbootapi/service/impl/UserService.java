package com.xxbb.springbootapi.service.impl;

import com.auth0.jwt.JWTCreator;
import com.xxbb.springbootapi.config.AppConfig;
import com.xxbb.springbootapi.dao.impl.UserDao;
import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.entity.dto.*;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.service.IUserService;
import com.xxbb.springbootapi.utils.OrikaUtil;
import com.xxbb.springbootapi.utils.jwt.JwtUtil;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;
    @Autowired(required = false)
    UserDao userDao;

    /*           *
     * 登录
     *
     * @param input
     * @return
     */
    @Override
    public JsonResultData<LoginResult> login(LoginInput input) {

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
        LoginResult loginResult = getLoginResult(user);
        //生成token
        JWTCreator.Builder builder = JwtUtil.createBuilder();
        builder.withClaim("id", user.getId());//用户id
        builder.withClaim("username", user.getUsername());//用户账号
        builder.withClaim("authority", loginResult.getAuthorityValues());//用户权限
        builder.withClaim("name", user.getName());//用户姓名
        builder.withClaim("roleId", user.getRoleId());//用户角色id
        String token = JwtUtil.getToken(builder, 60 * 60 * 12);
        return new JsonResultData<LoginResult>().Success(loginResult.setToken(token));
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
     *
     * @param entity
     * @return
     */
    @Override
    public boolean add(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));//加密密码
        //判断账号是否存在
        User user = mapper().findOne(mapper().query().where.username().eq(entity.getUsername()).end());
        if (user != null) {
            throw new LegalException("账号已存在");
        }
        return userDao.insert(entity) > 0;
    }

    @Override
    public boolean update(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));//加密密码
        return userDao.update(entity) > 0;
    }

    //删除
    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员为系统内置账号，不可删除");
        } else if (id == getCurrentUser().getUserInfo().getId()) {
            throw new LegalException("当前登录账号为你自己的账号，不可删除");
        }
        return super.delete(id);
    }

    private LoginResult getLoginResult(User user) {
        //获取用户权限
        Role role = roleService.find(user.getRoleId());
        //1.获取当前角色拥有的权限,管理员全查
        List<RoleAuthority> roleAuthorities = roleAuthorityService.mapper().listEntity(roleAuthorityService.mapper().query().where.applyIf(role.getId() != 1, x -> x.roleId().eq(role.getId())).end());
        //2.获取当前角色拥有的权限的id列表
        List<Integer> roleAuthorityIdList = roleAuthorities.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        //3.查询包含在权限的id列表里面的权限,获取权限列表,如果不是管理员就按角色查询，且roleAuthorityIdList不为空，如果roleAuthorityIdList为空，则表示没有权限，则查询id为null的数据以实现返回空数据的目的
        List<Authority> authorities = authorityService.mapper().listEntity(authorityService.mapper().query().where().applyIf(role.getId() != 1, x -> x.applyIf(roleAuthorityIdList.size() > 0, o -> o.id().in(roleAuthorityIdList)).applyIf(roleAuthorityIdList.size() == 0, o -> o.id().isNull())).end());
        //4.获取权限列表里面的权限值，获取列表
        List<String> authorityList = authorities.stream().map(Authority::getValue).collect(Collectors.toList());
        //5.构建用户角色权限信息
        List<RoleAuthorityResult> roleAuthorityResultList = OrikaUtil.converts(roleAuthorities, RoleAuthorityResult.class);
        roleAuthorityResultList.forEach(roleAuthorityResult -> {
            Authority auth = authorities.stream().filter(authority -> authority.getId().equals(roleAuthorityResult.getAuthorityId())).findFirst().orElse(null);
            roleAuthorityResult.setAuthorityName(auth != null ? auth.getName() : null);
            roleAuthorityResult.setAuthorityValue(auth != null ? auth.getValue() : null);
            roleAuthorityResult.setRoleName(role.getName());
        });
        return new LoginResult().setUserInfo(user.setPassword(null)).setAuthorityValues(authorityList).setRoleAuthority(roleAuthorityResultList);
    }

    /**
     * 当前登录用户，只含有id，username，name，roleId
     *
     * @return
     */
    @Override
    public LoginResult getCurrentUser() {
        //从SecurityUserDetails获取用户信息
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getLoginResult(user);
    }

}