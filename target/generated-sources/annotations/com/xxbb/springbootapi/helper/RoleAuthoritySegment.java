package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.RoleAuthorityMapping.*;

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
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;

/**
 *
 * RoleAuthoritySegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface RoleAuthoritySegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R authorityId() {
      return this.set(authorityId);
    }

    default R createTime() {
      return this.set(createTime);
    }

    default R isDeleted() {
      return this.set(isDeleted);
    }

    default R roleId() {
      return this.set(roleId);
    }

    default R updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, RoleAuthorityQuery> implements ASegment<Selector> {
    public Selector(RoleAuthorityQuery query) {
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

    public Selector authorityId(String _alias_) {
      return this.process(authorityId, _alias_);
    }

    public Selector createTime(String _alias_) {
      return this.process(createTime, _alias_);
    }

    public Selector isDeleted(String _alias_) {
      return this.process(isDeleted, _alias_);
    }

    public Selector roleId(String _alias_) {
      return this.process(roleId, _alias_);
    }

    public Selector updateTime(String _alias_) {
      return this.process(updateTime, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, RoleAuthorityQuery>, U extends IWrapper<?, U, RoleAuthorityQuery>> extends WhereBase<W, U, RoleAuthorityQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, RoleAuthorityQuery> id() {
      return this.set(id);
    }

    public NumericWhere<W, RoleAuthorityQuery> authorityId() {
      return this.set(authorityId);
    }

    public ObjectWhere<W, RoleAuthorityQuery> createTime() {
      return this.set(createTime);
    }

    public NumericWhere<W, RoleAuthorityQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public NumericWhere<W, RoleAuthorityQuery> roleId() {
      return this.set(roleId);
    }

    public ObjectWhere<W, RoleAuthorityQuery> updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, RoleAuthorityQuery> {
    public QueryWhere(RoleAuthorityQuery query) {
      super(query);
    }

    private QueryWhere(RoleAuthorityQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((RoleAuthorityQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, RoleAuthorityUpdate> {
    public UpdateWhere(RoleAuthorityUpdate updater) {
      super(updater);
    }

    private UpdateWhere(RoleAuthorityUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((RoleAuthorityUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, RoleAuthorityQuery> implements ASegment<GroupBy> {
    public GroupBy(RoleAuthorityQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, RoleAuthorityQuery> implements ASegment<HavingOperator<Having>> {
    public Having(RoleAuthorityQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, RoleAuthorityQuery> implements ASegment<OrderByApply<QueryOrderBy, RoleAuthorityQuery>> {
    public QueryOrderBy(RoleAuthorityQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, RoleAuthorityUpdate> implements ASegment<OrderByApply<UpdateOrderBy, RoleAuthorityUpdate>> {
    public UpdateOrderBy(RoleAuthorityUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, RoleAuthorityUpdate> implements ASegment<UpdateApply<UpdateSetter, RoleAuthorityUpdate>> {
    public UpdateSetter(RoleAuthorityUpdate updater) {
      super(updater);
    }
  }
}
