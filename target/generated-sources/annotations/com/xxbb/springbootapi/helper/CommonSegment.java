package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.CommonMapping.*;

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
import com.xxbb.springbootapi.wrapper.CommonQuery;
import com.xxbb.springbootapi.wrapper.CommonUpdate;

/**
 *
 * CommonSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface CommonSegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R createTime() {
      return this.set(createTime);
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
  final class Selector extends SelectorBase<Selector, CommonQuery> implements ASegment<Selector> {
    public Selector(CommonQuery query) {
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
  abstract class EntityWhere<W extends WhereBase<W, U, CommonQuery>, U extends IWrapper<?, U, CommonQuery>> extends WhereBase<W, U, CommonQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, CommonQuery> id() {
      return this.set(id);
    }

    public ObjectWhere<W, CommonQuery> createTime() {
      return this.set(createTime);
    }

    public ObjectWhere<W, CommonQuery> updateTime() {
      return this.set(updateTime);
    }

    public BooleanWhere<W, CommonQuery> isDeleted() {
      return this.set(isDeleted);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, CommonQuery> {
    public QueryWhere(CommonQuery query) {
      super(query);
    }

    private QueryWhere(CommonQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((CommonQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, CommonUpdate> {
    public UpdateWhere(CommonUpdate updater) {
      super(updater);
    }

    private UpdateWhere(CommonUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((CommonUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, CommonQuery> implements ASegment<GroupBy> {
    public GroupBy(CommonQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, CommonQuery> implements ASegment<HavingOperator<Having>> {
    public Having(CommonQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, CommonQuery> implements ASegment<OrderByApply<QueryOrderBy, CommonQuery>> {
    public QueryOrderBy(CommonQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, CommonUpdate> implements ASegment<OrderByApply<UpdateOrderBy, CommonUpdate>> {
    public UpdateOrderBy(CommonUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, CommonUpdate> implements ASegment<UpdateApply<UpdateSetter, CommonUpdate>> {
    public UpdateSetter(CommonUpdate updater) {
      super(updater);
    }
  }
}
