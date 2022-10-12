package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.CodeGenRecordMapping.*;

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
import com.xxbb.springbootapi.wrapper.CodeGenRecordQuery;
import com.xxbb.springbootapi.wrapper.CodeGenRecordUpdate;

/**
 *
 * CodeGenRecordSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface CodeGenRecordSegment {
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
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, CodeGenRecordQuery> implements ASegment<Selector> {
    public Selector(CodeGenRecordQuery query) {
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
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, CodeGenRecordQuery>, U extends IWrapper<?, U, CodeGenRecordQuery>> extends WhereBase<W, U, CodeGenRecordQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, CodeGenRecordQuery> id() {
      return this.set(id);
    }

    public ObjectWhere<W, CodeGenRecordQuery> createTime() {
      return this.set(createTime);
    }

    public NumericWhere<W, CodeGenRecordQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public ObjectWhere<W, CodeGenRecordQuery> updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, CodeGenRecordQuery> {
    public QueryWhere(CodeGenRecordQuery query) {
      super(query);
    }

    private QueryWhere(CodeGenRecordQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((CodeGenRecordQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, CodeGenRecordUpdate> {
    public UpdateWhere(CodeGenRecordUpdate updater) {
      super(updater);
    }

    private UpdateWhere(CodeGenRecordUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((CodeGenRecordUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, CodeGenRecordQuery> implements ASegment<GroupBy> {
    public GroupBy(CodeGenRecordQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, CodeGenRecordQuery> implements ASegment<HavingOperator<Having>> {
    public Having(CodeGenRecordQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, CodeGenRecordQuery> implements ASegment<OrderByApply<QueryOrderBy, CodeGenRecordQuery>> {
    public QueryOrderBy(CodeGenRecordQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, CodeGenRecordUpdate> implements ASegment<OrderByApply<UpdateOrderBy, CodeGenRecordUpdate>> {
    public UpdateOrderBy(CodeGenRecordUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, CodeGenRecordUpdate> implements ASegment<UpdateApply<UpdateSetter, CodeGenRecordUpdate>> {
    public UpdateSetter(CodeGenRecordUpdate updater) {
      super(updater);
    }
  }
}
