package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.PeoperMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.helper.PeoperSegment;
import java.util.Optional;

/**
 *
 * PeoperQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class PeoperQuery extends BaseQuery<Peoper, PeoperQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final PeoperSegment.Selector select = new PeoperSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final PeoperSegment.GroupBy groupBy = new PeoperSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final PeoperSegment.Having having = new PeoperSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final PeoperSegment.QueryOrderBy orderBy = new PeoperSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final PeoperSegment.QueryWhere where = new PeoperSegment.QueryWhere(this);

  public PeoperQuery() {
    this(true, null, () -> null, null);
  }

  public PeoperQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public PeoperQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public PeoperQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Peoper.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public PeoperSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public PeoperSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static PeoperQuery emptyQuery() {
    return new PeoperQuery(false, null, () -> null, null);
  }

  public static PeoperQuery emptyQuery(String alias) {
    return new PeoperQuery(false, null, () -> alias, null);
  }

  public static PeoperQuery emptyQuery(StringSupplier table) {
    return new PeoperQuery(false, fragment(table), () -> null, null);
  }

  public static PeoperQuery query() {
    return new PeoperQuery();
  }

  public static PeoperQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static PeoperQuery query(String alias) {
    return new PeoperQuery(alias);
  }

  public static PeoperQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new PeoperQuery(true, fragment(table), () -> null, null);
  }

  public static PeoperQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new PeoperQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static PeoperQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new PeoperQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
