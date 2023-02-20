package com.xxbb.springbootapi.service.impl;

import com.auth0.jwt.JWTCreator;
import com.xxbb.springbootapi.config.AppConfig;
import com.xxbb.springbootapi.dao.impl.SysUserDao;
import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.entity.SysRole;
import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.entity.SysUser;
import com.xxbb.springbootapi.entity.dto.*;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.SysUserMapper;
import com.xxbb.springbootapi.service.ISysSysUserService;
import com.xxbb.springbootapi.utils.OrikaUtil;
import com.xxbb.springbootapi.utils.jwt.JwtUtil;
import com.xxbb.springbootapi.wrapper.SysUserQuery;
import com.xxbb.springbootapi.wrapper.SysUserUpdate;
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
public class SysUserService extends SysBaseService<SysUser, SysUserQuery, SysUserUpdate, SysUserMapper> implements ISysSysUserService {
    @Autowired
    private AppConfig appConfig;
    @Qualifier("sysUserMapper")
    @Autowired(required = false)
    SysUserMapper mapper;
    @Autowired(required = false)
    SysUserQuery query;
    @Autowired
    private SysRoleAuthorityService sysRoleAuthorityService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysAuthorityService sysAuthorityService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;
    @Autowired(required = false)
    SysUserDao userDao;
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
        SysUser sysUser = userDetails.getSysUser();
        //获取用户权限
        LoginResult loginResult = getLoginResult(sysUser);
        //生成token
        JWTCreator.Builder builder = JwtUtil.createBuilder();
        builder.withClaim("id", sysUser.getId());//用户id
        builder.withClaim("username", sysUser.getUsername());//用户账号
        builder.withClaim("authority", loginResult.getAuthorityValues());//用户权限
        builder.withClaim("name", sysUser.getName());//用户姓名
        builder.withClaim("roleId", sysUser.getRoleId());//用户角色id
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
    public boolean add(SysUser entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));//加密密码
        //判断账号是否存在
        SysUser sysUser = mapper().findOne(mapper().query().where.username().eq(entity.getUsername()).end());
        if (sysUser != null) {
            throw new LegalException("账号已存在");
        }
        return userDao.insert(entity) > 0;
    }

    @Override
    public boolean update(SysUser entity) {
        if (entity.getRoleId() == 1 && getCurrentUser().getSysUserInfo().getRoleId() != 1) {
            throw new LegalException("超级管理员为内置账号，您无权操作！");
        }
        if (entity.getPassword() == null) {
            entity.setPassword(dao.find(entity.getId()).getPassword());
        } else {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));//加密密码
        }
        return userDao.update(entity) > 0;
    }

    //删除
    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员为系统内置账号，不可删除");
        } else if (id == getCurrentUser().getSysUserInfo().getId()) {
            throw new LegalException("当前登录账号为你自己的账号，不可删除");
        }
        return super.delete(id);
    }

    private LoginResult getLoginResult(SysUser sysUser) {
        //获取用户权限
        SysRole sysRole = sysRoleService.find(sysUser.getRoleId());
        //1.获取当前角色拥有的权限,管理员全查
        List<SysRoleAuthority> roleAuthorities = sysRoleAuthorityService.mapper().listEntity(sysRoleAuthorityService.mapper().query().where.applyIf(sysRole.getId() != 1, x -> x.roleId().eq(sysRole.getId())).end());
        //2.获取当前角色拥有的权限的id列表
        List<Integer> roleAuthorityIdList = roleAuthorities.stream().map(SysRoleAuthority::getAuthorityId).collect(Collectors.toList());
        //3.查询包含在权限的id列表里面的权限,获取权限列表,如果不是管理员就按角色查询，且roleAuthorityIdList不为空，如果roleAuthorityIdList为空，则表示没有权限，则查询id为null的数据以实现返回空数据的目的
        List<SysAuthority> authorities = sysAuthorityService.mapper().listEntity(sysAuthorityService.mapper().query().where().applyIf(sysRole.getId() != 1, x -> x.applyIf(roleAuthorityIdList.size() > 0, o -> o.id().in(roleAuthorityIdList)).applyIf(roleAuthorityIdList.size() == 0, o -> o.id().isNull())).end());
        //4.获取权限列表里面的权限值，获取列表
        List<String> authorityList = authorities.stream().map(SysAuthority::getValue).collect(Collectors.toList());
        //5.构建用户角色权限信息
        List<SysRoleAuthorityResult> roleAuthorityResultList = OrikaUtil.converts(roleAuthorities, SysRoleAuthorityResult.class);
        roleAuthorityResultList.forEach(roleAuthorityResult -> {
            SysAuthority auth = authorities.stream().filter(authority -> authority.getId().equals(roleAuthorityResult.getAuthorityId())).findFirst().orElse(null);
            roleAuthorityResult.setAuthorityName(auth != null ? auth.getName() : null);
            roleAuthorityResult.setAuthorityValue(auth != null ? auth.getValue() : null);
            roleAuthorityResult.setRoleName(sysRole.getName());
        });
        return new LoginResult().setSysUserInfo(sysUser.setPassword(null)).setAuthorityValues(authorityList).setRoleAuthority(roleAuthorityResultList);
    }

    /**
     * 当前登录用户，只含有id，username，name，roleId
     *
     * @return
     */
    @Override
    public LoginResult getCurrentUser() {
        //从SecurityUserDetails获取用户信息
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getLoginResult(sysUser);
    }

}