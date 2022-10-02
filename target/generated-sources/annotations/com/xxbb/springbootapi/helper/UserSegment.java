package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.UserMapping.*;

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
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;

/**
 *
 * UserSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface UserSegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R age() {
      return this.set(age);
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

    default R sex() {
      return this.set(sex);
    }

    default R updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, UserQuery> implements ASegment<Selector> {
    public Selector(UserQuery query) {
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

    public Selector age(String _alias_) {
      return this.process(age, _alias_);
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

    public Selector sex(String _alias_) {
      return this.process(sex, _alias_);
    }

    public Selector updateTime(String _alias_) {
      return this.process(updateTime, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, UserQuery>, U extends IWrapper<?, U, UserQuery>> extends WhereBase<W, U, UserQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, UserQuery> id() {
      return this.set(id);
    }

    public NumericWhere<W, UserQuery> age() {
      return this.set(age);
    }

    public ObjectWhere<W, UserQuery> createTime() {
      return this.set(createTime);
    }

    public NumericWhere<W, UserQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public StringWhere<W, UserQuery> name() {
      return this.set(name);
    }

    public NumericWhere<W, UserQuery> sex() {
      return this.set(sex);
    }

    public ObjectWhere<W, UserQuery> updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, UserQuery> {
    public QueryWhere(UserQuery query) {
      super(query);
    }

    private QueryWhere(UserQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((UserQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, UserUpdate> {
    public UpdateWhere(UserUpdate updater) {
      super(updater);
    }

    private UpdateWhere(UserUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((UserUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, UserQuery> implements ASegment<GroupBy> {
    public GroupBy(UserQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, UserQuery> implements ASegment<HavingOperator<Having>> {
    public Having(UserQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, UserQuery> implements ASegment<OrderByApply<QueryOrderBy, UserQuery>> {
    public QueryOrderBy(UserQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, UserUpdate> implements ASegment<OrderByApply<UpdateOrderBy, UserUpdate>> {
    public UpdateOrderBy(UserUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, UserUpdate> implements ASegment<UpdateApply<UpdateSetter, UserUpdate>> {
    public UpdateSetter(UserUpdate updater) {
      super(updater);
    }
  }
}
