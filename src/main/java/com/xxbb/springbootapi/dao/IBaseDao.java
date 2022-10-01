package com.xxbb.springbootapi.dao;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.entity.DataList;

import java.util.List;

public interface IBaseDao<K extends Common, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> {

    E mapper();
    /*
     * 增加
     * */
    int add(K entity);

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    int add(List<K> list);

    /*
     * 删除
     * */
    int delete(int id);

    /*
     * 修改
     * */
    int update(K entity);

    /*
     * 查询一个
     * */
    K find(int id);

    /*
     * 查询列表，限定条数
     * */
    List<K> list(int limit);

    /*
     * 查询列表，所有数据
     * */
    List<K> list();

    /*
     * 查询列表，分页查询
     * */
    DataList<K> pagination(int index, int limit);
}
