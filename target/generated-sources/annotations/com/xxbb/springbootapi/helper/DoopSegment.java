package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.DoopMapping.*;

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
import cn.org.atool.fluent.mybatis.segment.where.BooleanWhere;
import cn.org.atool.fluent.mybatis.segment.where.NumericWhere;
import cn.org.atool.fluent.mybatis.segment.where.ObjectWhere;
import cn.org.atool.fluent.mybatis.segment.where.StringWhere;
import com.xxbb.springbootapi.wrapper.DoopQuery;
import com.xxbb.springbootapi.wrapper.DoopUpdate;

/**
 *
 * DoopSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface DoopSegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R createTime() {
      return this.set(createTime);
    }

    default R qww() {
      return this.set(qww);
    }

    default R updateTime() {
      return this.set(updateTime);
    }

    default R isDeleted() {
      return this.set(isDeleted);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, DoopQuery> implements ASegment<Selector> {
    public Selector(DoopQuery query) {
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

    public Selector createTime(String _alias_) {
      return this.process(createTime, _alias_);
    }

    public Selector qww(String _alias_) {
      return this.process(qww, _alias_);
    }

    public Selector updateTime(String _alias_) {
      return this.process(updateTime, _alias_);
    }

    public Selector isDeleted(String _alias_) {
      return this.process(isDeleted, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, DoopQuery>, U extends IWrapper<?, U, DoopQuery>> extends WhereBase<W, U, DoopQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, DoopQuery> id() {
      return this.set(id);
    }

    public ObjectWhere<W, DoopQuery> createTime() {
      return this.set(createTime);
    }

    public StringWhere<W, DoopQuery> qww() {
      return this.set(qww);
    }

    public ObjectWhere<W, DoopQuery> updateTime() {
      return this.set(updateTime);
    }

    public BooleanWhere<W, DoopQuery> isDeleted() {
      return this.set(isDeleted);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, DoopQuery> {
    public QueryWhere(DoopQuery query) {
      super(query);
    }

    private QueryWhere(DoopQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((DoopQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, DoopUpdate> {
    public UpdateWhere(DoopUpdate updater) {
      super(updater);
    }

    private UpdateWhere(DoopUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((DoopUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, DoopQuery> implements ASegment<GroupBy> {
    public GroupBy(DoopQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, DoopQuery> implements ASegment<HavingOperator<Having>> {
    public Having(DoopQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, DoopQuery> implements ASegment<OrderByApply<QueryOrderBy, DoopQuery>> {
    public QueryOrderBy(DoopQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, DoopUpdate> implements ASegment<OrderByApply<UpdateOrderBy, DoopUpdate>> {
    public UpdateOrderBy(DoopUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, DoopUpdate> implements ASegment<UpdateApply<UpdateSetter, DoopUpdate>> {
    public UpdateSetter(DoopUpdate updater) {
      super(updater);
    }
  }
}
