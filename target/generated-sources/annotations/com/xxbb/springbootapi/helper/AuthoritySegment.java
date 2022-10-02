package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.AuthorityMapping.*;

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
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;

/**
 *
 * AuthoritySegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface AuthoritySegment {
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

    default R name() {
      return this.set(name);
    }

    default R updateTime() {
      return this.set(updateTime);
    }

    default R value() {
      return this.set(value);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, AuthorityQuery> implements ASegment<Selector> {
    public Selector(AuthorityQuery query) {
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

    public Selector name(String _alias_) {
      return this.process(name, _alias_);
    }

    public Selector updateTime(String _alias_) {
      return this.process(updateTime, _alias_);
    }

    public Selector value(String _alias_) {
      return this.process(value, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, AuthorityQuery>, U extends IWrapper<?, U, AuthorityQuery>> extends WhereBase<W, U, AuthorityQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, AuthorityQuery> id() {
      return this.set(id);
    }

    public ObjectWhere<W, AuthorityQuery> createTime() {
      return this.set(createTime);
    }

    public NumericWhere<W, AuthorityQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public StringWhere<W, AuthorityQuery> name() {
      return this.set(name);
    }

    public ObjectWhere<W, AuthorityQuery> updateTime() {
      return this.set(updateTime);
    }

    public StringWhere<W, AuthorityQuery> value() {
      return this.set(value);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, AuthorityQuery> {
    public QueryWhere(AuthorityQuery query) {
      super(query);
    }

    private QueryWhere(AuthorityQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((AuthorityQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, AuthorityUpdate> {
    public UpdateWhere(AuthorityUpdate updater) {
      super(updater);
    }

    private UpdateWhere(AuthorityUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((AuthorityUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, AuthorityQuery> implements ASegment<GroupBy> {
    public GroupBy(AuthorityQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, AuthorityQuery> implements ASegment<HavingOperator<Having>> {
    public Having(AuthorityQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, AuthorityQuery> implements ASegment<OrderByApply<QueryOrderBy, AuthorityQuery>> {
    public QueryOrderBy(AuthorityQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, AuthorityUpdate> implements ASegment<OrderByApply<UpdateOrderBy, AuthorityUpdate>> {
    public UpdateOrderBy(AuthorityUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, AuthorityUpdate> implements ASegment<UpdateApply<UpdateSetter, AuthorityUpdate>> {
    public UpdateSetter(AuthorityUpdate updater) {
      super(updater);
    }
  }
}
