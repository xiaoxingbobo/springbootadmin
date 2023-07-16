package com.xxbb.springbootapi.dao.impl;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.mapper.IWrapperMapper;
import cn.org.atool.fluent.mybatis.base.model.UniqueType;
import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.xxbb.springbootapi.dao.ISysBaseDao;
import com.xxbb.springbootapi.entity.SysCommon;
import com.xxbb.springbootapi.entity.dto.PagedInput;
import com.xxbb.springbootapi.entity.dto.PagedInputT;
import com.xxbb.springbootapi.entity.dto.PagedResult;
import com.xxbb.springbootapi.entity.dto.Search;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class SysBaseDao<K extends SysCommon, T extends BaseQuery<K, T>, V extends BaseUpdate<K, V, T>, E extends IWrapperMapper<K, T, V>> implements ISysBaseDao<K, T, V, E> {

    @Autowired(required = false)
    public E mapper;

    public E mapper() {
        return mapper;
    }


    /**
     * 添加
     *
     * @param entity
     * @return
     */
    @Override
    public int insert(K entity) {
        if (entity.findPk() == null) {
            return mapper.insert(entity);
        } else {
            return mapper.insertWithPk(entity);
        }
    }

    /**
     * 批量添加
     *
     * @param list
     * @return
     */

    @Override
    public int insert(List<K> list) {
        return mapper.insertBatch(list);
    }

    /**
     * 删除，通过主键
     *
     * @param id
     * @return
     */
    @Override
    public int delete(int id) {
        return mapper.deleteById(id);
    }

    /**
     * 批量删除，通过多个主键
     *
     * @param ids
     * @return
     */
    @Override
    public int delete(List<Integer> ids) {
        return mapper.deleteByIds(Arrays.asList(ids.toArray()));
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public int logicDelete(int id) {
        return mapper.logicDeleteById(id);
    }

    /**
     * 逻辑删除，通过多个主键
     *
     * @param ids
     * @return
     */
    @Override
    public int logicDelete(List<Integer> ids) {
        return mapper.logicDeleteByIds(ids);
    }

    /**
     * 根据主键更新
     *
     * @param entity
     * @return
     */
    @Override
    public int update(K entity) {
        return mapper.updateById(entity);
    }

    /*
     * 查询一个，通过主键
     */
    @Override
    public K find(int id) {
        return mapper.findById(id);
    }


    /**
     * 查询列表，传入一个搜索字段
     *
     * @param
     * @return
     */

    @Override
    public List<K> search(Search search) {

        T query = mapper().query().where().applyIf(!search.getField().isEmpty() && !search.getKeyword().isEmpty(), x -> x.apply(search.getField()).like(search.getKeyword())).end();
        return mapper.listEntity(query.distinct());
    }

    /**
     * 查询列表，传入多个搜索字段
     *
     * @param searches
     * @return
     */
    @Override
    public List<K> search(List<Search> searches) {
        T query = mapper.query().selectAll();
        searches.forEach(search -> query.where().applyIf(!search.getField().isEmpty() && !search.getKeyword().isEmpty(), x -> x.apply(search.getField()).like(search.getKeyword())).end());
        return mapper().listEntity(query);
    }


    /*
     * 查询列表，限定条数，无筛选条件
     * */
    @Override
    public List<K> list(int limit) {
        return mapper.listEntity(mapper.query().limit(limit));
    }

    /*
     * 查询列表，所有数据，无筛选条件
     * */
    @Override
    public List<K> list() {
        return mapper.listEntity(mapper.query().selectAll());
    }

    /**
     * 查询列表，传入查询语句，自定义筛选条件
     *
     * @param query
     * @return
     */
    @Override
    public List<K> list(T query) {
        return mapper.listEntity(query);
    }

    /**
     * 查询列表，传入实体类，通过实体类的属性筛选
     *
     * @param pagedInputT
     * @return
     */

    @Override
    public PagedResult<K> list(PagedInputT<K> pagedInputT) {
        T query = mapper.query().where().eqByEntity(pagedInputT.getCondition()).end();
        //分页
        int index = pagedInputT.getSize() * (pagedInputT.getCurrent() - 1);
        StdPagedList<K> pagedList = mapper.stdPagedEntity(query.limit(index, pagedInputT.getSize()));
        return new PagedResult<K>().setTotal(pagedList.getTotal()).setPages(pagedList.getTotal() / pagedInputT.getSize() + 1).setCurrent(pagedInputT.getCurrent()).setSize(pagedInputT.getSize()).setData(pagedList.getData());
    }

    @Override
    public List<K> list(K entity) {
        return mapper.listEntity(mapper.query().where().applyIf(entity != null, x -> x.eqByEntity(entity)).end());
    }

    /**
     * 查询列表，多个主键，通过主键筛选
     *
     * @param ids
     * @return
     */
    @Override
    public List<K> list(List<Integer> ids) {
        T query = mapper.query().where().applyIf(ids.size() > 0, x -> x.apply(mapper.query().fieldName(UniqueType.PRIMARY_ID)).in(ids)).end();
        return mapper().listEntity(query.selectAll());
    }


    /*
     * 查询列表，分页查询，无筛选条件
     * */
    @Override
    public PagedResult<K> paged(PagedInput pagedInput) {
        PagedResult<K> pagedResult = new PagedResult<>();
        int index = pagedInput.getSize() * (pagedInput.getCurrent() - 1);
        StdPagedList<K> pagedList = mapper.stdPagedEntity(mapper.query().limit(index, pagedInput.getSize()));
        return pagedResult.setPages(pagedList.getTotal() / pagedInput.getSize() + 1).setCurrent(pagedInput.getCurrent()).setSize(pagedInput.getSize()).setTotal(pagedList.getTotal()).setData(pagedList.getData());
    }
    /**
     * 查询列表，传入多个搜索字段,和搜索字段，分页
     *
     * @param input
     * @return
     */

    @Override
    public PagedResult<K> paged(PagedInputT<K> input) {
        T query = mapper.query().selectAll();
        if (input.getSearches() != null) {
            //搜索
            input.getSearches().forEach(i -> query.where().applyIf(!i.getField().isEmpty() && !i.getKeyword().isEmpty(), x -> x.apply(i.getField()).like(i.getKeyword())).end());
        }
        //筛选
        query.where().applyIf(input.getCondition()!=null,x->x.eqByEntity(input.getCondition()));
        //分页
        int index = input.getSize() * (input.getCurrent() - 1);
        StdPagedList<K> pagedList = mapper.stdPagedEntity(query.limit(index, input.getSize()));
        return new PagedResult<K>().setPages(pagedList.getTotal() / input.getSize() + 1).setCurrent(input.getCurrent()).setSize(input.getSize()).setData(pagedList.getData()).setTotal(pagedList.getTotal());
    }


}
