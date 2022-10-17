package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.nameMapping.*;

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
import com.xxbb.springbootapi.wrapper.nameQuery;
import com.xxbb.springbootapi.wrapper.nameUpdate;

/**
 *
 * nameSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface nameSegment {
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

    default R 测试name() {
      return this.set(测试name);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, nameQuery> implements ASegment<Selector> {
    public Selector(nameQuery query) {
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

    public Selector 测试name(String _alias_) {
      return this.process(测试name, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, nameQuery>, U extends IWrapper<?, U, nameQuery>> extends WhereBase<W, U, nameQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, nameQuery> id() {
      return this.set(id);
    }

    public ObjectWhere<W, nameQuery> createTime() {
      return this.set(createTime);
    }

    public NumericWhere<W, nameQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public ObjectWhere<W, nameQuery> updateTime() {
      return this.set(updateTime);
    }

    public BooleanWhere<W, nameQuery> 测试name() {
      return this.set(测试name);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, nameQuery> {
    public QueryWhere(nameQuery query) {
      super(query);
    }

    private QueryWhere(nameQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((nameQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, nameUpdate> {
    public UpdateWhere(nameUpdate updater) {
      super(updater);
    }

    private UpdateWhere(nameUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((nameUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, nameQuery> implements ASegment<GroupBy> {
    public GroupBy(nameQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, nameQuery> implements ASegment<HavingOperator<Having>> {
    public Having(nameQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, nameQuery> implements ASegment<OrderByApply<QueryOrderBy, nameQuery>> {
    public QueryOrderBy(nameQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, nameUpdate> implements ASegment<OrderByApply<UpdateOrderBy, nameUpdate>> {
    public UpdateOrderBy(nameUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, nameUpdate> implements ASegment<UpdateApply<UpdateSetter, nameUpdate>> {
    public UpdateSetter(nameUpdate updater) {
      super(updater);
    }
  }
}
