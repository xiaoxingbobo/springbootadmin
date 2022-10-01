/**
 * 第一个版本
 **/

//package com.xxbb.springbootapi.dao.impl;
//
//import cn.org.atool.fluent.mybatis.base.mapper.IEntityMapper;
//import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
//import com.xxbb.springbootapi.dao.IBaseDao;
//import com.xxbb.springbootapi.entity.Common;
//import com.xxbb.springbootapi.entity.DataList;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class BaseDao<T extends IEntityMapper<K>, K extends Common> implements IBaseDao<T, K> {
//    @Autowired(required = false)
//    public T mapper;
//    @Autowired(required = false)
//    public IWrapperMapper<K> wrapper;
//
//    @Override
//    public int add(K entity) {
//        if (entity.findPk() == null) {
//            return mapper.insert(entity);
//        } else {
//            return mapper.insertWithPk(entity);
//        }
//    }
//    @Override
//    public int add(List<K> list) {
//        return mapper.insertBatch(list);
//    }
//
//    @Override
//    public int delete(int id) {
//        return mapper.deleteById(id);
//    }
//
//    @Override
//    public int update(K entity) {
//        return mapper.updateById(entity);
//    }
//
//    /*
//     * 查询一个
//     * */
//    @Override
//    public K find(int id) {
//        return mapper.findById(id);
//    }
//
//    /*
//     * 查询列表，限定条数
//     * */
//    @Override
//    public List<K> list(int limit) {
//        List<K> objs = mapper.listEntity(wrapper.query().limit(limit));
//        return objs;
//    }
//
//    /*
//     * 查询列表，所有数据
//     * */
//    @Override
//    public List<K> list() {
//        List<K> objs = mapper.listEntity(wrapper.query().selectAll());
//        return objs;
//    }
//
//    /*
//     * 查询列表，分页查询
//     * */
//    @Override
//    public DataList<K> pagination(int index, int limit) {
//        DataList<K> dataList = new DataList<>();
//        List<K> objs = mapper.listEntity(wrapper.query().limit(index, limit));
//        int count = mapper.count(wrapper.query().selectAll());
//        dataList.setTotal(count).setList(objs);
//        return dataList;
//    }
//}