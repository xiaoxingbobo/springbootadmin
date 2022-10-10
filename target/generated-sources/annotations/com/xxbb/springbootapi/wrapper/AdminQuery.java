package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.AdminMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Admin;
import com.xxbb.springbootapi.helper.AdminSegment;
import java.util.Optional;

/**
 *
 * AdminQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class AdminQuery extends BaseQuery<Admin, AdminQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final AdminSegment.Selector select = new AdminSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final AdminSegment.GroupBy groupBy = new AdminSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final AdminSegment.Having having = new AdminSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final AdminSegment.QueryOrderBy orderBy = new AdminSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final AdminSegment.QueryWhere where = new AdminSegment.QueryWhere(this);

  public AdminQuery() {
    this(true, null, () -> null, null);
  }

  public AdminQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public AdminQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public AdminQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Admin.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public AdminSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public AdminSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static AdminQuery emptyQuery() {
    return new AdminQuery(false, null, () -> null, null);
  }

  public static AdminQuery emptyQuery(String alias) {
    return new AdminQuery(false, null, () -> alias, null);
  }

  public static AdminQuery emptyQuery(StringSupplier table) {
    return new AdminQuery(false, fragment(table), () -> null, null);
  }

  public static AdminQuery query() {
    return new AdminQuery();
  }

  public static AdminQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static AdminQuery query(String alias) {
    return new AdminQuery(alias);
  }

  public static AdminQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new AdminQuery(true, fragment(table), () -> null, null);
  }

  public static AdminQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new AdminQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static AdminQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new AdminQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
