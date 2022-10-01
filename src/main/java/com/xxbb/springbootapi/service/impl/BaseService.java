package com.xxbb.springbootapi.service.impl;


import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.dao.impl.BaseDao;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.entity.DataList;
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

        initEntity(entity);
        return dao.add(entity) > 0;
    }

    @Override
    public boolean add(List<K> list) {
//        for (K it : list) {
//            initEntity(it);
//        }
        return dao.add(list) > 0;
    }

    public void initEntity(K entity) {
        if (entity.getId() == null) {
            entity.setCreateTime(new Date());
            entity.setId(null);
            entity.setIsDeleted(0);
        }
        entity.setUpdateTime(new Date());
    }


    @Override
    public boolean delete(int id) {
        return dao.delete(id) > 0;
    }

    @Override
    public boolean update(K entity) {
        initEntity(entity);
        return dao.update(entity) > 0;
    }

    @Override
    public K find(int id) {
        return dao.find(id);
    }

    @Override
    public List<K> list(int limit) {
        return  dao.list(limit);
    }

    @Override
    public List<K> list() {
        return  dao.list();
    }

    @Override
    public DataList<K> pagination(int index, int limit) {
        return  dao.pagination(index, limit);
    }
}