package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.AccountMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.helper.AccountSegment;
import java.util.Optional;

/**
 *
 * AccountQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class AccountQuery extends BaseQuery<Account, AccountQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final AccountSegment.Selector select = new AccountSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final AccountSegment.GroupBy groupBy = new AccountSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final AccountSegment.Having having = new AccountSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final AccountSegment.QueryOrderBy orderBy = new AccountSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final AccountSegment.QueryWhere where = new AccountSegment.QueryWhere(this);

  public AccountQuery() {
    this(true, null, () -> null, null);
  }

  public AccountQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public AccountQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public AccountQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Account.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public AccountSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public AccountSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static AccountQuery emptyQuery() {
    return new AccountQuery(false, null, () -> null, null);
  }

  public static AccountQuery emptyQuery(String alias) {
    return new AccountQuery(false, null, () -> alias, null);
  }

  public static AccountQuery emptyQuery(StringSupplier table) {
    return new AccountQuery(false, fragment(table), () -> null, null);
  }

  public static AccountQuery query() {
    return new AccountQuery();
  }

  public static AccountQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static AccountQuery query(String alias) {
    return new AccountQuery(alias);
  }

  public static AccountQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new AccountQuery(true, fragment(table), () -> null, null);
  }

  public static AccountQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new AccountQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static AccountQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new AccountQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
