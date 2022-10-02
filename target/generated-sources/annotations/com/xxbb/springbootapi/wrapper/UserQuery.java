package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.UserMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.helper.UserSegment;
import java.util.Optional;

/**
 *
 * UserQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class UserQuery extends BaseQuery<User, UserQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final UserSegment.Selector select = new UserSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final UserSegment.GroupBy groupBy = new UserSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final UserSegment.Having having = new UserSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final UserSegment.QueryOrderBy orderBy = new UserSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final UserSegment.QueryWhere where = new UserSegment.QueryWhere(this);

  public UserQuery() {
    this(true, null, () -> null, null);
  }

  public UserQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public UserQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public UserQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, User.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public UserSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public UserSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static UserQuery emptyQuery() {
    return new UserQuery(false, null, () -> null, null);
  }

  public static UserQuery emptyQuery(String alias) {
    return new UserQuery(false, null, () -> alias, null);
  }

  public static UserQuery emptyQuery(StringSupplier table) {
    return new UserQuery(false, fragment(table), () -> null, null);
  }

  public static UserQuery query() {
    return new UserQuery();
  }

  public static UserQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static UserQuery query(String alias) {
    return new UserQuery(alias);
  }

  public static UserQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new UserQuery(true, fragment(table), () -> null, null);
  }

  public static UserQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new UserQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static UserQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new UserQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
