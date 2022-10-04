package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.CommonMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.helper.CommonSegment;
import java.util.Optional;

/**
 *
 * CommonQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class CommonQuery extends BaseQuery<Common, CommonQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final CommonSegment.Selector select = new CommonSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final CommonSegment.GroupBy groupBy = new CommonSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final CommonSegment.Having having = new CommonSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final CommonSegment.QueryOrderBy orderBy = new CommonSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final CommonSegment.QueryWhere where = new CommonSegment.QueryWhere(this);

  public CommonQuery() {
    this(true, null, () -> null, null);
  }

  public CommonQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public CommonQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public CommonQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Common.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public CommonSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public CommonSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static CommonQuery emptyQuery() {
    return new CommonQuery(false, null, () -> null, null);
  }

  public static CommonQuery emptyQuery(String alias) {
    return new CommonQuery(false, null, () -> alias, null);
  }

  public static CommonQuery emptyQuery(StringSupplier table) {
    return new CommonQuery(false, fragment(table), () -> null, null);
  }

  public static CommonQuery query() {
    return new CommonQuery();
  }

  public static CommonQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static CommonQuery query(String alias) {
    return new CommonQuery(alias);
  }

  public static CommonQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new CommonQuery(true, fragment(table), () -> null, null);
  }

  public static CommonQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new CommonQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static CommonQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new CommonQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
