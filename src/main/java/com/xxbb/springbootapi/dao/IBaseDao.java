package com.xxbb.springbootapi.dao;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.entity.dto.PagedInput;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;

import java.util.List;

public interface IBaseDao<K extends Common, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> {

    E mapper();

    /*
     * 增加
     * */

    int insert(K entity);

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    int insert(List<K> list);

    /*
     * 删除
     * */
    int delete(int id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int delete(List<Integer> ids);

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



    /**
     * 通过关键字搜索列表
     * @param search
     * @return
     */
    List<K> search(Search search);
    /**
     * 多条件搜索
     * @param searches
     * @return
     */
    List<K> search(List<Search> searches);

    PagedResult<K> searchPaged(PagedInputC<List<Search>> pagedInputCondition);

    /**
     * 动态条件查询
     *
     * @param query
     * @return
     */
    List<K> list(T query);

    PagedResult<K> list(PagedInputC<K> pagedInputC);

    List<K> list(K entity);

    /**
     * 通过ids查询列表
     * @param ids
     * @return
     */
    List<K> list(List<Integer> ids);


    /*
     * 查询列表，分页查询
     * */
    PagedResult<K> paged(PagedInput pagedInput);
}
