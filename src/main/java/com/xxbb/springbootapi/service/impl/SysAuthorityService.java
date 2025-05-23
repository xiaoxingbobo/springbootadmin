package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.SysAuthority;
import com.xxbb.springbootapi.entity.SysRoleAuthority;
import com.xxbb.springbootapi.entity.dto.LoginResult;
import com.xxbb.springbootapi.entity.dto.MenuDTO;
import com.xxbb.springbootapi.entity.dto.MenuResult;
import com.xxbb.springbootapi.entity.dto.Meta;
import com.xxbb.springbootapi.entity.dto.enums.AuthorityType;
import com.xxbb.springbootapi.interceptor.LegalException;
import com.xxbb.springbootapi.mapper.SysAuthorityMapper;
import com.xxbb.springbootapi.service.ISysAuthorityService;
import com.xxbb.springbootapi.utils.OrikaUtil;
import com.xxbb.springbootapi.wrapper.SysAuthorityQuery;
import com.xxbb.springbootapi.wrapper.SysAuthorityUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysAuthorityService extends SysBaseService<SysAuthority, SysAuthorityQuery, SysAuthorityUpdate, SysAuthorityMapper> implements ISysAuthorityService {

    @Autowired
    private SysUserService userService;

    @Override
    public boolean delete(int id) {
        if (id == 1) {
            throw new LegalException("超级管理员权限不允许删除");
        }
        return super.delete(id);
    }

    @Override
    public List<MenuDTO> viewMenu() {
        //筛选
        LoginResult loginResult = userService.getCurrentUser();
        List<Integer> authorityIds = loginResult.getRoleAuthority().stream().map(SysRoleAuthority::getAuthorityId).collect(Collectors.toList());
        /**authorityType().eq(AuthorityType.ROUTER)只查询路由
         * loginResult.getUserInfo().getRoleId() != 1判断角色是否为管理员，不是则执行x->x.id().in(authorityIds)，否则查询所有
         *
         */

        List<MenuResult> menuResultList = OrikaUtil.converts(dao.mapper().listEntity(dao.mapper().query().where().authorityType().eq(AuthorityType.ROUTER).applyIf(loginResult.getSysUserInfo().getRoleId() != 1, x -> x.id().in(authorityIds)).end()), MenuResult.class);
        //递归处理子集
        menuResultList = getMenu(menuResultList, 0);
        return OrikaUtil.converts(menuResultList, MenuDTO.class);
    }

    public List<MenuResult> getMenu(List<MenuResult> list, Integer parentId) {
        List<MenuResult> menuResultList = list.stream().filter(x -> x.getParentId().equals(parentId)).collect(Collectors.toList());
        menuResultList.forEach(x -> {
            x.setMeta(new Meta().setIcon(x.getIcon()).setTitle(x.getTitle()));
            x.setChildren(getMenu(list, x.getId()));//递归
        });
        return menuResultList;
    }
}
