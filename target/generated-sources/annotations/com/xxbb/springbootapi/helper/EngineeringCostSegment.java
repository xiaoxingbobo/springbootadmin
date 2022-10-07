package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.EngineeringCostMapping.*;

import cn.org.atool.fluent.mybatis.base.crud.IWrapper;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.functions.IAggregate;
import cn.org.atool.fluent.mybatis.segment.GroupByBase;
import cn.org.atool.fluent.mybatis.segment.HavingBase;
import cn.org.atool.fluent.mybatis.segment.HavingOperator;
import cn.org.atool.fluent.mybatis.segment.OrderByApply;
import cn.org.atool.fluent.mybatis.segment.OrderByBase;
import cn.org.atool.fluent.mybatis.segment.SelectorBase;
import cn.org.atool.fluent.mybatis.segment.UpdateApply;
import cn.org.atool.fluent.mybatis.segment.UpdateBase;
import cn.org.atool.fluent.mybatis.segment.WhereBase;
import cn.org.atool.fluent.mybatis.segment.where.NumericWhere;
import cn.org.atool.fluent.mybatis.segment.where.ObjectWhere;
import cn.org.atool.fluent.mybatis.segment.where.StringWhere;
import com.xxbb.springbootapi.wrapper.EngineeringCostQuery;
import com.xxbb.springbootapi.wrapper.EngineeringCostUpdate;

/**
 *
 * EngineeringCostSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface EngineeringCostSegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R constructionCost() {
      return this.set(constructionCost);
    }

    default R createTime() {
      return this.set(createTime);
    }

    default R independentFees() {
      return this.set(independentFees);
    }

    default R isDeleted() {
      return this.set(isDeleted);
    }

    default R name() {
      return this.set(name);
    }

    default R purchaseExpense() {
      return this.set(purchaseExpense);
    }

    default R total() {
      return this.set(total);
    }

    default R updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, EngineeringCostQuery> implements ASegment<Selector> {
    public Selector(EngineeringCostQuery query) {
      super(query);
    }

    protected Selector(Selector selector, IAggregate aggregate) {
      super(selector, aggregate);
    }

    @Override
    protected Selector aggregateSegment(IAggregate aggregate) {
      return new Selector(this, aggregate);
    }

    public Selector id(String _alias_) {
      return this.process(id, _alias_);
    }

    public Selector constructionCost(String _alias_) {
      return this.process(constructionCost, _alias_);
    }

    public Selector createTime(String _alias_) {
      return this.process(createTime, _alias_);
    }

    public Selector independentFees(String _alias_) {
      return this.process(independentFees, _alias_);
    }

    public Selector isDeleted(String _alias_) {
      return this.process(isDeleted, _alias_);
    }

    public Selector name(String _alias_) {
      return this.process(name, _alias_);
    }

    public Selector purchaseExpense(String _alias_) {
      return this.process(purchaseExpense, _alias_);
    }

    public Selector total(String _alias_) {
      return this.process(total, _alias_);
    }

    public Selector updateTime(String _alias_) {
      return this.process(updateTime, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, EngineeringCostQuery>, U extends IWrapper<?, U, EngineeringCostQuery>> extends WhereBase<W, U, EngineeringCostQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, EngineeringCostQuery> id() {
      return this.set(id);
    }

    public NumericWhere<W, EngineeringCostQuery> constructionCost() {
      return this.set(constructionCost);
    }

    public ObjectWhere<W, EngineeringCostQuery> createTime() {
      return this.set(createTime);
    }

    public NumericWhere<W, EngineeringCostQuery> independentFees() {
      return this.set(independentFees);
    }

    public NumericWhere<W, EngineeringCostQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public StringWhere<W, EngineeringCostQuery> name() {
      return this.set(name);
    }

    public NumericWhere<W, EngineeringCostQuery> purchaseExpense() {
      return this.set(purchaseExpense);
    }

    public NumericWhere<W, EngineeringCostQuery> total() {
      return this.set(total);
    }

    public ObjectWhere<W, EngineeringCostQuery> updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, EngineeringCostQuery> {
    public QueryWhere(EngineeringCostQuery query) {
      super(query);
    }

    private QueryWhere(EngineeringCostQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((EngineeringCostQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, EngineeringCostUpdate> {
    public UpdateWhere(EngineeringCostUpdate updater) {
      super(updater);
    }

    private UpdateWhere(EngineeringCostUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((EngineeringCostUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, EngineeringCostQuery> implements ASegment<GroupBy> {
    public GroupBy(EngineeringCostQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, EngineeringCostQuery> implements ASegment<HavingOperator<Having>> {
    public Having(EngineeringCostQuery query) {
      super(query);
    }

    protected Having(Having having, IAggregate aggregate) {
      super(having, aggregate);
    }

    @Override
    protected Having aggregateSegment(IAggregate aggregate) {
      return new Having(this, aggregate);
    }
  }

  /**
   * Query OrderBy设置
   */
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, EngineeringCostQuery> implements ASegment<OrderByApply<QueryOrderBy, EngineeringCostQuery>> {
    public QueryOrderBy(EngineeringCostQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, EngineeringCostUpdate> implements ASegment<OrderByApply<UpdateOrderBy, EngineeringCostUpdate>> {
    public UpdateOrderBy(EngineeringCostUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, EngineeringCostUpdate> implements ASegment<UpdateApply<UpdateSetter, EngineeringCostUpdate>> {
    public UpdateSetter(EngineeringCostUpdate updater) {
      super(updater);
    }
  }
}
