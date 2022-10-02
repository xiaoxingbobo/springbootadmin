package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.RoleAuthorityMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.helper.RoleAuthoritySegment;
import java.util.Optional;

/**
 *
 * RoleAuthorityQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class RoleAuthorityQuery extends BaseQuery<RoleAuthority, RoleAuthorityQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final RoleAuthoritySegment.Selector select = new RoleAuthoritySegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final RoleAuthoritySegment.GroupBy groupBy = new RoleAuthoritySegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final RoleAuthoritySegment.Having having = new RoleAuthoritySegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final RoleAuthoritySegment.QueryOrderBy orderBy = new RoleAuthoritySegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final RoleAuthoritySegment.QueryWhere where = new RoleAuthoritySegment.QueryWhere(this);

  public RoleAuthorityQuery() {
    this(true, null, () -> null, null);
  }

  public RoleAuthorityQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public RoleAuthorityQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public RoleAuthorityQuery(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, RoleAuthority.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public RoleAuthoritySegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public RoleAuthoritySegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static RoleAuthorityQuery emptyQuery() {
    return new RoleAuthorityQuery(false, null, () -> null, null);
  }

  public static RoleAuthorityQuery emptyQuery(String alias) {
    return new RoleAuthorityQuery(false, null, () -> alias, null);
  }

  public static RoleAuthorityQuery emptyQuery(StringSupplier table) {
    return new RoleAuthorityQuery(false, fragment(table), () -> null, null);
  }

  public static RoleAuthorityQuery query() {
    return new RoleAuthorityQuery();
  }

  public static RoleAuthorityQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static RoleAuthorityQuery query(String alias) {
    return new RoleAuthorityQuery(alias);
  }

  public static RoleAuthorityQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new RoleAuthorityQuery(true, fragment(table), () -> null, null);
  }

  public static RoleAuthorityQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new RoleAuthorityQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static RoleAuthorityQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new RoleAuthorityQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
