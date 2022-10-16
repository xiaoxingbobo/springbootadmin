package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.HaMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Ha;
import com.xxbb.springbootapi.helper.HaSegment;
import java.util.Optional;

/**
 *
 * HaQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class HaQuery extends BaseQuery<Ha, HaQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final HaSegment.Selector select = new HaSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final HaSegment.GroupBy groupBy = new HaSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final HaSegment.Having having = new HaSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final HaSegment.QueryOrderBy orderBy = new HaSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final HaSegment.QueryWhere where = new HaSegment.QueryWhere(this);

  public HaQuery() {
    this(true, null, () -> null, null);
  }

  public HaQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public HaQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public HaQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Ha.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public HaSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public HaSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static HaQuery emptyQuery() {
    return new HaQuery(false, null, () -> null, null);
  }

  public static HaQuery emptyQuery(String alias) {
    return new HaQuery(false, null, () -> alias, null);
  }

  public static HaQuery emptyQuery(StringSupplier table) {
    return new HaQuery(false, fragment(table), () -> null, null);
  }

  public static HaQuery query() {
    return new HaQuery();
  }

  public static HaQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static HaQuery query(String alias) {
    return new HaQuery(alias);
  }

  public static HaQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new HaQuery(true, fragment(table), () -> null, null);
  }

  public static HaQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new HaQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static HaQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new HaQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
