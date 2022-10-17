package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.nameMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.helper.nameSegment;
import java.util.Optional;

/**
 *
 * nameQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class nameQuery extends BaseQuery<name, nameQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final nameSegment.Selector select = new nameSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final nameSegment.GroupBy groupBy = new nameSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final nameSegment.Having having = new nameSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final nameSegment.QueryOrderBy orderBy = new nameSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final nameSegment.QueryWhere where = new nameSegment.QueryWhere(this);

  public nameQuery() {
    this(true, null, () -> null, null);
  }

  public nameQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public nameQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public nameQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, name.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public nameSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public nameSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static nameQuery emptyQuery() {
    return new nameQuery(false, null, () -> null, null);
  }

  public static nameQuery emptyQuery(String alias) {
    return new nameQuery(false, null, () -> alias, null);
  }

  public static nameQuery emptyQuery(StringSupplier table) {
    return new nameQuery(false, fragment(table), () -> null, null);
  }

  public static nameQuery query() {
    return new nameQuery();
  }

  public static nameQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static nameQuery query(String alias) {
    return new nameQuery(alias);
  }

  public static nameQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new nameQuery(true, fragment(table), () -> null, null);
  }

  public static nameQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new nameQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static nameQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new nameQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
