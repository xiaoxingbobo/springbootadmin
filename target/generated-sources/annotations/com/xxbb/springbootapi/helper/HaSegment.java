package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.HaMapping.*;

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
import com.xxbb.springbootapi.wrapper.HaQuery;
import com.xxbb.springbootapi.wrapper.HaUpdate;

/**
 *
 * HaSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface HaSegment {
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

    default R test() {
      return this.set(test);
    }

    default R tex() {
      return this.set(tex);
    }

    default R tst() {
      return this.set(tst);
    }

    default R tt() {
      return this.set(tt);
    }

    default R updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, HaQuery> implements ASegment<Selector> {
    public Selector(HaQuery query) {
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

    public Selector test(String _alias_) {
      return this.process(test, _alias_);
    }

    public Selector tex(String _alias_) {
      return this.process(tex, _alias_);
    }

    public Selector tst(String _alias_) {
      return this.process(tst, _alias_);
    }

    public Selector tt(String _alias_) {
      return this.process(tt, _alias_);
    }

    public Selector updateTime(String _alias_) {
      return this.process(updateTime, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, HaQuery>, U extends IWrapper<?, U, HaQuery>> extends WhereBase<W, U, HaQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, HaQuery> id() {
      return this.set(id);
    }

    public ObjectWhere<W, HaQuery> createTime() {
      return this.set(createTime);
    }

    public NumericWhere<W, HaQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public StringWhere<W, HaQuery> test() {
      return this.set(test);
    }

    public StringWhere<W, HaQuery> tex() {
      return this.set(tex);
    }

    public StringWhere<W, HaQuery> tst() {
      return this.set(tst);
    }

    public StringWhere<W, HaQuery> tt() {
      return this.set(tt);
    }

    public ObjectWhere<W, HaQuery> updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, HaQuery> {
    public QueryWhere(HaQuery query) {
      super(query);
    }

    private QueryWhere(HaQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((HaQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, HaUpdate> {
    public UpdateWhere(HaUpdate updater) {
      super(updater);
    }

    private UpdateWhere(HaUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((HaUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, HaQuery> implements ASegment<GroupBy> {
    public GroupBy(HaQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, HaQuery> implements ASegment<HavingOperator<Having>> {
    public Having(HaQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, HaQuery> implements ASegment<OrderByApply<QueryOrderBy, HaQuery>> {
    public QueryOrderBy(HaQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, HaUpdate> implements ASegment<OrderByApply<UpdateOrderBy, HaUpdate>> {
    public UpdateOrderBy(HaUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, HaUpdate> implements ASegment<UpdateApply<UpdateSetter, HaUpdate>> {
    public UpdateSetter(HaUpdate updater) {
      super(updater);
    }
  }
}
