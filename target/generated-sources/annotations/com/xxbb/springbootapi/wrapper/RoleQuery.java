package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.RoleMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.helper.RoleSegment;
import java.util.Optional;

/**
 *
 * RoleQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class RoleQuery extends BaseQuery<Role, RoleQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final RoleSegment.Selector select = new RoleSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final RoleSegment.GroupBy groupBy = new RoleSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final RoleSegment.Having having = new RoleSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final RoleSegment.QueryOrderBy orderBy = new RoleSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final RoleSegment.QueryWhere where = new RoleSegment.QueryWhere(this);

  public RoleQuery() {
    this(true, null, () -> null, null);
  }

  public RoleQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public RoleQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public RoleQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Role.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public RoleSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public RoleSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static RoleQuery emptyQuery() {
    return new RoleQuery(false, null, () -> null, null);
  }

  public static RoleQuery emptyQuery(String alias) {
    return new RoleQuery(false, null, () -> alias, null);
  }

  public static RoleQuery emptyQuery(StringSupplier table) {
    return new RoleQuery(false, fragment(table), () -> null, null);
  }

  public static RoleQuery query() {
    return new RoleQuery();
  }

  public static RoleQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static RoleQuery query(String alias) {
    return new RoleQuery(alias);
  }

  public static RoleQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new RoleQuery(true, fragment(table), () -> null, null);
  }

  public static RoleQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new RoleQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static RoleQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new RoleQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
