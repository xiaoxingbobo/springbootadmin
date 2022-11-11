package com.xxbb.springbootapi.controller;

import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("authority")
@Api(tags = "Authority表")
@RestController
public class AuthorityController extends AuthApiController<Authority, AuthorityQuery, AuthorityUpdate, AuthorityMapper> {
    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:add')")
    public Boolean create(Authority entity) {
        return super.create(entity);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:delete')")
    public Boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:update')")
    public Boolean delete(List<Integer> ids) {
        return super.delete(ids);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:update')")
    public Boolean update(Authority entity) {
        return super.update(entity);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:select')")
    public List<Authority> select(List<Search> searches) {
        return super.select(searches);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:select')")
    public Authority find(int id) {
        return super.find(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:select')")
    public List<Authority> select(int limit) {
        return super.select(limit);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:select')")
    public List<Authority> select() {
        return super.select();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:select')")
    public PagedResult<Authority> select(PagedInputC<Authority> input) {
        return super.select(input);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:select')")
    public PagedResult<Authority> paged(int current, int size) {
        return super.paged(current, size);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('sys:authority:select')")
    public PagedResult<Authority> search(PagedInputC<List<Search>> pagedInput) {
        return super.search(pagedInput);
    }
}
