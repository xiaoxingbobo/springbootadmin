package com.xxbb.springbootapi.service;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.entity.DataList;

import java.util.List;


/**
 * 基础Service接口
 *
 * @param <T>
 * @param <K>
 */
public interface IBaseService<K extends Common, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> {

    E mapper();

    /**
     * 初始化实体
     *
     * @param entity
     */
    void initEntity(K entity);

    /*
     * 增加
     * */
    boolean add(K entity);

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    boolean add(List<K> list);

    /*
     * 删除
     * */
    boolean delete(int id);

    /*
     * 修改
     * */
    boolean update(K entity);

    /*
     * 查询一个
     * */
    K find(int id);

    /*
     * 查询少量
     * */
    List<K> list(int limit);

    /*
     * 查询所有
     * */
    List<K> list();

    /*
     * 分页查询
     * */
    DataList<K> pagination(int index, int limit);


}
