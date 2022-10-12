package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.CodeGenRecordMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.helper.CodeGenRecordSegment;
import java.util.Optional;

/**
 *
 * CodeGenRecordQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class CodeGenRecordQuery extends BaseQuery<GenRecord, CodeGenRecordQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final CodeGenRecordSegment.Selector select = new CodeGenRecordSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final CodeGenRecordSegment.GroupBy groupBy = new CodeGenRecordSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final CodeGenRecordSegment.Having having = new CodeGenRecordSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final CodeGenRecordSegment.QueryOrderBy orderBy = new CodeGenRecordSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final CodeGenRecordSegment.QueryWhere where = new CodeGenRecordSegment.QueryWhere(this);

  public CodeGenRecordQuery() {
    this(true, null, () -> null, null);
  }

  public CodeGenRecordQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public CodeGenRecordQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public CodeGenRecordQuery(boolean defaults, IFragment table, StringSupplier alias,
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
  public CodeGenRecordSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public CodeGenRecordSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static CodeGenRecordQuery emptyQuery() {
    return new CodeGenRecordQuery(false, null, () -> null, null);
  }

  public static CodeGenRecordQuery emptyQuery(String alias) {
    return new CodeGenRecordQuery(false, null, () -> alias, null);
  }

  public static CodeGenRecordQuery emptyQuery(StringSupplier table) {
    return new CodeGenRecordQuery(false, fragment(table), () -> null, null);
  }

  public static CodeGenRecordQuery query() {
    return new CodeGenRecordQuery();
  }

  public static CodeGenRecordQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static CodeGenRecordQuery query(String alias) {
    return new CodeGenRecordQuery(alias);
  }

  public static CodeGenRecordQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new CodeGenRecordQuery(true, fragment(table), () -> null, null);
  }

  public static CodeGenRecordQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new CodeGenRecordQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static CodeGenRecordQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new CodeGenRecordQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
