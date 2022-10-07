package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static cn.org.atool.fluent.mybatis.utility.StrConstant.EMPTY;
import static com.xxbb.springbootapi.helper.EngineeringCostMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseQuery;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.BracketFrag;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.EngineeringCost;
import com.xxbb.springbootapi.helper.EngineeringCostSegment;
import java.util.Optional;

/**
 *
 * EngineeringCostQuery: 查询构造
 *
 * @author powered by FluentMybatis
 */
public class EngineeringCostQuery extends BaseQuery<EngineeringCost, EngineeringCostQuery> {
  /**
   * 指定查询字段, 默认无需设置
   */
  public final EngineeringCostSegment.Selector select = new EngineeringCostSegment.Selector(this);

  /**
   * 分组：GROUP BY 字段, ...
   * 例: groupBy('id', 'name')
   */
  public final EngineeringCostSegment.GroupBy groupBy = new EngineeringCostSegment.GroupBy(this);

  /**
   * 分组条件设置 having...
   */
  public final EngineeringCostSegment.Having having = new EngineeringCostSegment.Having(this);

  /**
   * 排序设置 order by ...
   */
  public final EngineeringCostSegment.QueryOrderBy orderBy = new EngineeringCostSegment.QueryOrderBy(this);

  /**
   * 查询条件 where ...
   */
  public final EngineeringCostSegment.QueryWhere where = new EngineeringCostSegment.QueryWhere(this);

  public EngineeringCostQuery() {
    this(true, null, () -> null, null);
  }

  public EngineeringCostQuery(String alias) {
    this(true, null, () -> alias, null);
  }

  public EngineeringCostQuery(boolean defaults, IFragment table, String alias, Parameters shared) {
    this(defaults, table, () -> alias, shared);
  }

  public EngineeringCostQuery(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, EngineeringCost.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setQueryDefault(this);
    }
  }

  @Override
  public EngineeringCostSegment.QueryWhere where() {
    return this.where;
  }

  @Override
  public EngineeringCostSegment.QueryOrderBy orderBy() {
    return this.orderBy;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static EngineeringCostQuery emptyQuery() {
    return new EngineeringCostQuery(false, null, () -> null, null);
  }

  public static EngineeringCostQuery emptyQuery(String alias) {
    return new EngineeringCostQuery(false, null, () -> alias, null);
  }

  public static EngineeringCostQuery emptyQuery(StringSupplier table) {
    return new EngineeringCostQuery(false, fragment(table), () -> null, null);
  }

  public static EngineeringCostQuery query() {
    return new EngineeringCostQuery();
  }

  public static EngineeringCostQuery defaultQuery() {
    return query();
  }

  /**
   * 显式指定表别名(join查询的时候需要定义表别名)
   */
  public static EngineeringCostQuery query(String alias) {
    return new EngineeringCostQuery(alias);
  }

  public static EngineeringCostQuery query(StringSupplier table) {
    assertNotNull("table", table);
    return new EngineeringCostQuery(true, fragment(table), () -> null, null);
  }

  public static EngineeringCostQuery query(StringSupplier table, String alias) {
    assertNotNull("table", table);
    return new EngineeringCostQuery(true, fragment(table), () -> alias, null);
  }

  /**
   * select * from (select query) alias
   * @param query 子查询
   * @param alias 子查询别名
   */
  public static EngineeringCostQuery query(IQuery query, String alias) {
    assertNotNull("query", query);
    return new EngineeringCostQuery(true, BracketFrag.set(query), () -> alias, null);
  }
}
