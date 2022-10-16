package com.xxbb.springbootapi.entity.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.entity.helper.RoleAuthorityResultMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.entity.helper.RoleAuthorityResultSegment;
import java.util.Optional;

/**
 *
 * RoleAuthorityResultQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class RoleAuthorityResultQuery extends BaseQuery<RoleAuthorityResult, RoleAuthorityResultQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final RoleAuthorityResultSegment.Selector select = new RoleAuthorityResultSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final RoleAuthorityResultSegment.GroupBy groupBy = new RoleAuthorityResultSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final RoleAuthorityResultSegment.Having having = new RoleAuthorityResultSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final RoleAuthorityResultSegment.QueryOrderBy orderBy = new RoleAuthorityResultSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final RoleAuthorityResultSegment.QueryWhere where = new RoleAuthorityResultSegment.QueryWhere(this);

  public RoleAuthorityResultQuery() {
    this(true, null, () -> null, null);
  }

  public RoleAuthorityResultQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public RoleAuthorityResultQuery(boolean defaults, IFragment table, String alias,
      Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public RoleAuthorityResultQuery(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, RoleAuthorityResult.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public RoleAuthorityResultSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public RoleAuthorityResultSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static RoleAuthorityResultQuery emptyQuery() {
    return new RoleAuthorityResultQuery(false, null, () -> null, null);
  }

  public static RoleAuthorityResultQuery emptyQuery(String alias) {
    return new RoleAuthorityResultQuery(false, null, () -> alias, null);
  }

  public static RoleAuthorityResultQuery emptyQuery(StringSupplier table) {
    return new RoleAuthorityResultQuery(false, fragment(table), () -> null, null);
  }

  public static RoleAuthorityResultQuery query() {
    return new RoleAuthorityResultQuery();
  }

  public static RoleAuthorityResultQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static RoleAuthorityResultQuery query(String alias) {
    return new RoleAuthorityResultQuery(alias);
  }

  public static RoleAuthorityResultQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new RoleAuthorityResultQuery(true, fragment(table), () -> null, null);
  }

  public static RoleAuthorityResultQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new RoleAuthorityResultQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static RoleAuthorityResultQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new RoleAuthorityResultQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
