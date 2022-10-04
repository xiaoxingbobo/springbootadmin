package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.TestMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.helper.TestSegment;
import java.util.Optional;

/**
 *
 * TestQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class TestQuery extends BaseQuery<Test, TestQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final TestSegment.Selector select = new TestSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final TestSegment.GroupBy groupBy = new TestSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final TestSegment.Having having = new TestSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final TestSegment.QueryOrderBy orderBy = new TestSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final TestSegment.QueryWhere where = new TestSegment.QueryWhere(this);

  public TestQuery() {
    this(true, null, () -> null, null);
  }

  public TestQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public TestQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public TestQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Test.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public TestSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public TestSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static TestQuery emptyQuery() {
    return new TestQuery(false, null, () -> null, null);
  }

  public static TestQuery emptyQuery(String alias) {
    return new TestQuery(false, null, () -> alias, null);
  }

  public static TestQuery emptyQuery(StringSupplier table) {
    return new TestQuery(false, fragment(table), () -> null, null);
  }

  public static TestQuery query() {
    return new TestQuery();
  }

  public static TestQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static TestQuery query(String alias) {
    return new TestQuery(alias);
  }

  public static TestQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new TestQuery(true, fragment(table), () -> null, null);
  }

  public static TestQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new TestQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static TestQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new TestQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
