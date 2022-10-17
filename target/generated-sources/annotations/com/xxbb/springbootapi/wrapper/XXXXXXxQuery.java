package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.XXXXXXxMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.helper.XXXXXXxSegment;
import java.util.Optional;

/**
 *
 * XXXXXXxQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class XXXXXXxQuery extends BaseQuery<XXXXXXx, XXXXXXxQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final XXXXXXxSegment.Selector select = new XXXXXXxSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final XXXXXXxSegment.GroupBy groupBy = new XXXXXXxSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final XXXXXXxSegment.Having having = new XXXXXXxSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final XXXXXXxSegment.QueryOrderBy orderBy = new XXXXXXxSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final XXXXXXxSegment.QueryWhere where = new XXXXXXxSegment.QueryWhere(this);

  public XXXXXXxQuery() {
    this(true, null, () -> null, null);
  }

  public XXXXXXxQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public XXXXXXxQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public XXXXXXxQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, XXXXXXx.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public XXXXXXxSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public XXXXXXxSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static XXXXXXxQuery emptyQuery() {
    return new XXXXXXxQuery(false, null, () -> null, null);
  }

  public static XXXXXXxQuery emptyQuery(String alias) {
    return new XXXXXXxQuery(false, null, () -> alias, null);
  }

  public static XXXXXXxQuery emptyQuery(StringSupplier table) {
    return new XXXXXXxQuery(false, fragment(table), () -> null, null);
  }

  public static XXXXXXxQuery query() {
    return new XXXXXXxQuery();
  }

  public static XXXXXXxQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static XXXXXXxQuery query(String alias) {
    return new XXXXXXxQuery(alias);
  }

  public static XXXXXXxQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new XXXXXXxQuery(true, fragment(table), () -> null, null);
  }

  public static XXXXXXxQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new XXXXXXxQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static XXXXXXxQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new XXXXXXxQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
