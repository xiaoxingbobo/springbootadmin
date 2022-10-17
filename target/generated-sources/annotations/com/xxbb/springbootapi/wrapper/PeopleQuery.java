package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.PeopleMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.helper.PeopleSegment;
import java.util.Optional;

/**
 *
 * PeopleQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class PeopleQuery extends BaseQuery<People, PeopleQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final PeopleSegment.Selector select = new PeopleSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final PeopleSegment.GroupBy groupBy = new PeopleSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final PeopleSegment.Having having = new PeopleSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final PeopleSegment.QueryOrderBy orderBy = new PeopleSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final PeopleSegment.QueryWhere where = new PeopleSegment.QueryWhere(this);

  public PeopleQuery() {
    this(true, null, () -> null, null);
  }

  public PeopleQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public PeopleQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public PeopleQuery(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, People.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public PeopleSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public PeopleSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static PeopleQuery emptyQuery() {
    return new PeopleQuery(false, null, () -> null, null);
  }

  public static PeopleQuery emptyQuery(String alias) {
    return new PeopleQuery(false, null, () -> alias, null);
  }

  public static PeopleQuery emptyQuery(StringSupplier table) {
    return new PeopleQuery(false, fragment(table), () -> null, null);
  }

  public static PeopleQuery query() {
    return new PeopleQuery();
  }

  public static PeopleQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static PeopleQuery query(String alias) {
    return new PeopleQuery(alias);
  }

  public static PeopleQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new PeopleQuery(true, fragment(table), () -> null, null);
  }

  public static PeopleQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new PeopleQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static PeopleQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new PeopleQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
