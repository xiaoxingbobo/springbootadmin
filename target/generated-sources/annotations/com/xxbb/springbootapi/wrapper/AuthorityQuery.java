package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.AuthorityMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.helper.AuthoritySegment;
import java.util.Optional;

/**
 *
 * AuthorityQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class AuthorityQuery extends BaseQuery<Authority, AuthorityQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final AuthoritySegment.Selector select = new AuthoritySegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final AuthoritySegment.GroupBy groupBy = new AuthoritySegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final AuthoritySegment.Having having = new AuthoritySegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final AuthoritySegment.QueryOrderBy orderBy = new AuthoritySegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final AuthoritySegment.QueryWhere where = new AuthoritySegment.QueryWhere(this);

  public AuthorityQuery() {
    this(true, null, () -> null, null);
  }

  public AuthorityQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public AuthorityQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public AuthorityQuery(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, Authority.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public AuthoritySegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public AuthoritySegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static AuthorityQuery emptyQuery() {
    return new AuthorityQuery(false, null, () -> null, null);
  }

  public static AuthorityQuery emptyQuery(String alias) {
    return new AuthorityQuery(false, null, () -> alias, null);
  }

  public static AuthorityQuery emptyQuery(StringSupplier table) {
    return new AuthorityQuery(false, fragment(table), () -> null, null);
  }

  public static AuthorityQuery query() {
    return new AuthorityQuery();
  }

  public static AuthorityQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static AuthorityQuery query(String alias) {
    return new AuthorityQuery(alias);
  }

  public static AuthorityQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new AuthorityQuery(true, fragment(table), () -> null, null);
  }

  public static AuthorityQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new AuthorityQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static AuthorityQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new AuthorityQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
