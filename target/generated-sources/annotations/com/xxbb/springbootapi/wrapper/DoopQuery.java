package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.DoopMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.helper.DoopSegment;
import java.util.Optional;

/**
 *
 * DoopQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class DoopQuery extends BaseQuery<Doop, DoopQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final DoopSegment.Selector select = new DoopSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final DoopSegment.GroupBy groupBy = new DoopSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final DoopSegment.Having having = new DoopSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final DoopSegment.QueryOrderBy orderBy = new DoopSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final DoopSegment.QueryWhere where = new DoopSegment.QueryWhere(this);

  public DoopQuery() {
    this(true, null, () -> null, null);
  }

  public DoopQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public DoopQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public DoopQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Doop.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public DoopSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public DoopSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static DoopQuery emptyQuery() {
    return new DoopQuery(false, null, () -> null, null);
  }

  public static DoopQuery emptyQuery(String alias) {
    return new DoopQuery(false, null, () -> alias, null);
  }

  public static DoopQuery emptyQuery(StringSupplier table) {
    return new DoopQuery(false, fragment(table), () -> null, null);
  }

  public static DoopQuery query() {
    return new DoopQuery();
  }

  public static DoopQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static DoopQuery query(String alias) {
    return new DoopQuery(alias);
  }

  public static DoopQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new DoopQuery(true, fragment(table), () -> null, null);
  }

  public static DoopQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new DoopQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static DoopQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new DoopQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
