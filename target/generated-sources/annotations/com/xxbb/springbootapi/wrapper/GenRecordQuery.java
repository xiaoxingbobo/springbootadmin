package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.GenRecordMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.helper.GenRecordSegment;
import java.util.Optional;

/**
 *
 * GenRecordQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class GenRecordQuery extends BaseQuery<GenRecord, GenRecordQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final GenRecordSegment.Selector select = new GenRecordSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final GenRecordSegment.GroupBy groupBy = new GenRecordSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final GenRecordSegment.Having having = new GenRecordSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final GenRecordSegment.QueryOrderBy orderBy = new GenRecordSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final GenRecordSegment.QueryWhere where = new GenRecordSegment.QueryWhere(this);

  public GenRecordQuery() {
    this(true, null, () -> null, null);
  }

  public GenRecordQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public GenRecordQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public GenRecordQuery(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, GenRecord.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public GenRecordSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public GenRecordSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static GenRecordQuery emptyQuery() {
    return new GenRecordQuery(false, null, () -> null, null);
  }

  public static GenRecordQuery emptyQuery(String alias) {
    return new GenRecordQuery(false, null, () -> alias, null);
  }

  public static GenRecordQuery emptyQuery(StringSupplier table) {
    return new GenRecordQuery(false, fragment(table), () -> null, null);
  }

  public static GenRecordQuery query() {
    return new GenRecordQuery();
  }

  public static GenRecordQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static GenRecordQuery query(String alias) {
    return new GenRecordQuery(alias);
  }

  public static GenRecordQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new GenRecordQuery(true, fragment(table), () -> null, null);
  }

  public static GenRecordQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new GenRecordQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static GenRecordQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new GenRecordQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
