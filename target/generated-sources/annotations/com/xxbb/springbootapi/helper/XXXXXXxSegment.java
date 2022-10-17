package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.XXXXXXxMapping.*;

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
import com.xxbb.springbootapi.wrapper.XXXXXXxQuery;
import com.xxbb.springbootapi.wrapper.XXXXXXxUpdate;

/**
 *
 * XXXXXXxSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface XXXXXXxSegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R createTime() {
      return this.set(createTime);
    }

    default R isDeleted() {
      return this.set(isDeleted);
    }

    default R updateTime() {
      return this.set(updateTime);
    }

    default R xxxname() {
      return this.set(xxxname);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, XXXXXXxQuery> implements ASegment<Selector> {
    public Selector(XXXXXXxQuery query) {
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

    public Selector isDeleted(String _alias_) {
      return this.process(isDeleted, _alias_);
    }

    public Selector updateTime(String _alias_) {
      return this.process(updateTime, _alias_);
    }

    public Selector xxxname(String _alias_) {
      return this.process(xxxname, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, XXXXXXxQuery>, U extends IWrapper<?, U, XXXXXXxQuery>> extends WhereBase<W, U, XXXXXXxQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, XXXXXXxQuery> id() {
      return this.set(id);
    }

    public ObjectWhere<W, XXXXXXxQuery> createTime() {
      return this.set(createTime);
    }

    public NumericWhere<W, XXXXXXxQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public ObjectWhere<W, XXXXXXxQuery> updateTime() {
      return this.set(updateTime);
    }

    public BooleanWhere<W, XXXXXXxQuery> xxxname() {
      return this.set(xxxname);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, XXXXXXxQuery> {
    public QueryWhere(XXXXXXxQuery query) {
      super(query);
    }

    private QueryWhere(XXXXXXxQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((XXXXXXxQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, XXXXXXxUpdate> {
    public UpdateWhere(XXXXXXxUpdate updater) {
      super(updater);
    }

    private UpdateWhere(XXXXXXxUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((XXXXXXxUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, XXXXXXxQuery> implements ASegment<GroupBy> {
    public GroupBy(XXXXXXxQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, XXXXXXxQuery> implements ASegment<HavingOperator<Having>> {
    public Having(XXXXXXxQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, XXXXXXxQuery> implements ASegment<OrderByApply<QueryOrderBy, XXXXXXxQuery>> {
    public QueryOrderBy(XXXXXXxQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, XXXXXXxUpdate> implements ASegment<OrderByApply<UpdateOrderBy, XXXXXXxUpdate>> {
    public UpdateOrderBy(XXXXXXxUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, XXXXXXxUpdate> implements ASegment<UpdateApply<UpdateSetter, XXXXXXxUpdate>> {
    public UpdateSetter(XXXXXXxUpdate updater) {
      super(updater);
    }
  }
}
