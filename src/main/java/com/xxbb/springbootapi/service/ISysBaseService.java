package com.xxbb.springbootapi.service;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import com.xxbb.springbootapi.entity.SysCommon;
import com.xxbb.springbootapi.entity.dto.PagedInput;
import com.xxbb.springbootapi.entity.dto.PagedInputC;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;

import java.util.List;


/**
 * 基础Service接口
 *
 * @param <T>
 * @param <K>
 */
public interface ISysBaseService<K extends SysCommon, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> {

    E mapper();

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

    boolean delete(List<Integer> ids);

    boolean logicDelete(int id);

    boolean logicDelete(List<Integer> ids);

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

    List<K> list(K entity);

    List<K> list(List<Integer> ids);

    PagedResult<K> list(PagedInputC<K> input);

    /**
     * 搜索
     *
     * @param search
     * @return
     */
    List<K> search(Search search);
    List<K> search(List<Search> searches);


    PagedResult<K> searchPaged(PagedInputC<List<Search>> searches);

    /*
     * 分页查询
     * */
    PagedResult<K> paged(PagedInput input);
}
