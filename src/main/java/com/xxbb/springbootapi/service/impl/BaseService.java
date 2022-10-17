package com.xxbb.springbootapi.service.impl;


import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.dao.impl.BaseDao;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.entity.dto.PagedInput;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;
import com.xxbb.springbootapi.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public abstract class BaseService<K extends Common, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> implements IBaseService<K, T, V, E> {

    @Autowired(required = false)
    public BaseDao<K, T, V, E> dao;

    public E mapper() {
        return dao.mapper();
    }

    @Override
    public boolean add(K entity) {
        return dao.insert(entity) > 0;
    }

    @Override
    public boolean add(List<K> list) {
        return dao.insert(list) > 0;
    }

    /**
     * 初始化实体，设置创建时间，更新时间，若在IFMConfig中设置了自动填充，则不需要调用此方法
     *
     * @param entity
     */
    public void initEntity(K entity) {
        if (entity.getId() == null) {
            entity.setCreateTime(new Date());
            entity.setIsDeleted(Boolean.FALSE);
        }
        entity.setUpdateTime(new Date());
    }


    @Override
    public boolean delete(int id) {
        return dao.delete(id) > 0;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        return dao.delete(ids) > 0;
    }

    @Override
    public boolean logicDelete(int id) {
        return dao.logicDelete(id) > 0;
    }

    @Override
    public boolean logicDelete(List<Integer> ids) {
        return dao.logicDelete(ids) > 0;
    }

    @Override
    public boolean update(K entity) {
//        initEntity(entity);//使用IFMConfig中进行初始化
        return dao.update(entity) > 0;
    }

    @Override
    public K find(int id) {
        return dao.find(id);
    }

    @Override
    public List<K> list(int limit) {
        return dao.list(limit);
    }

    @Override
    public List<K> list() {
        return dao.list();
    }

    @Override
    public List<K> list(K entity) {
        return dao.list(entity);
    }

    @Override
    public List<K> list(List<Integer> ids) {
        return dao.list(ids);
    }

    @Override
    public PagedResult<K> list(PagedInputC<K> input) {
        return dao.list(input);
    }

    @Override
    public List<K> search(Search search) {
        return dao.search(search);
    }

    @Override
    public List<K> search(List<Search> searches) {
        return dao.search(searches);
    }

    @Override
    public PagedResult<K> searchPaged(PagedInputC<List<Search>> searches) {
        return dao.searchPaged(searches);
    }

    @Override
    public PagedResult<K> paged(PagedInput input) {
        return dao.paged(input);
    }
}