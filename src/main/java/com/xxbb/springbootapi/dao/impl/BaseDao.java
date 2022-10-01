package com.xxbb.springbootapi.dao.impl;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.dao.IBaseDao;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.entity.DataList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseDao<K extends Common, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> implements IBaseDao<K, T, V, E> {

    @Autowired(required = false)
    public E mapper;

    public E mapper() {
        return mapper;
    }


    @Override
    public int add(K entity) {
        if (entity.findPk() == null) {
            return mapper.insert(entity);
        } else {
            return mapper.insertWithPk(entity);
        }
    }

    @Override
    public int add(List<K> list) {
        return mapper.insertBatch(list);
    }

    @Override
    public int delete(int id) {
        return mapper.deleteById(id);
    }

    @Override
    public int update(K entity) {
        return mapper.updateById(entity);
    }

    /*
     * 查询一个
     * */
    @Override
    public K find(int id) {
        return mapper.findById(id);
    }

    /*
     * 查询列表，限定条数
     * */
    @Override
    public List<K> list(int limit) {
        return mapper.listEntity(mapper.query().limit(limit));
    }

    /*
     * 查询列表，所有数据
     * */
    @Override
    public List<K> list() {
        return mapper.listEntity(mapper.query().selectAll());
    }

    /*
     * 查询列表，分页查询
     * */
    @Override
    public DataList<K> pagination(int index, int limit) {
        DataList<K> dataList = new DataList<>();
        List<K> objs = mapper.listEntity(mapper.query().limit(index, limit));
        int count = mapper.count(mapper.query().selectAll());
        dataList.setTotal(count).setList(objs);
        return dataList;
    }

}
