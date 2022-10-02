package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.helper.AccountMapping.*;

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
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;

/**
 *
 * AccountSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface AccountSegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R account() {
      return this.set(account);
    }

    default R createTime() {
      return this.set(createTime);
    }

    default R email() {
      return this.set(email);
    }

    default R isDeleted() {
      return this.set(isDeleted);
    }

    default R password() {
      return this.set(password);
    }

    default R roleId() {
      return this.set(roleId);
    }

    default R updateTime() {
      return this.set(updateTime);
    }

    default R userId() {
      return this.set(userId);
    }
  }

  /**
   * select字段设置
   */
  final class Selector extends SelectorBase<Selector, AccountQuery> implements ASegment<Selector> {
    public Selector(AccountQuery query) {
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

    public Selector account(String _alias_) {
      return this.process(account, _alias_);
    }

    public Selector createTime(String _alias_) {
      return this.process(createTime, _alias_);
    }

    public Selector email(String _alias_) {
      return this.process(email, _alias_);
    }

    public Selector isDeleted(String _alias_) {
      return this.process(isDeleted, _alias_);
    }

    public Selector password(String _alias_) {
      return this.process(password, _alias_);
    }

    public Selector roleId(String _alias_) {
      return this.process(roleId, _alias_);
    }

    public Selector updateTime(String _alias_) {
      return this.process(updateTime, _alias_);
    }

    public Selector userId(String _alias_) {
      return this.process(userId, _alias_);
    }
  }

  /**
   * query/update where条件设置
   */
  abstract class EntityWhere<W extends WhereBase<W, U, AccountQuery>, U extends IWrapper<?, U, AccountQuery>> extends WhereBase<W, U, AccountQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, AccountQuery> id() {
      return this.set(id);
    }

    public StringWhere<W, AccountQuery> account() {
      return this.set(account);
    }

    public ObjectWhere<W, AccountQuery> createTime() {
      return this.set(createTime);
    }

    public StringWhere<W, AccountQuery> email() {
      return this.set(email);
    }

    public NumericWhere<W, AccountQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public StringWhere<W, AccountQuery> password() {
      return this.set(password);
    }

    public NumericWhere<W, AccountQuery> roleId() {
      return this.set(roleId);
    }

    public ObjectWhere<W, AccountQuery> updateTime() {
      return this.set(updateTime);
    }

    public NumericWhere<W, AccountQuery> userId() {
      return this.set(userId);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, AccountQuery> {
    public QueryWhere(AccountQuery query) {
      super(query);
    }

    private QueryWhere(AccountQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((AccountQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, AccountUpdate> {
    public UpdateWhere(AccountUpdate updater) {
      super(updater);
    }

    private UpdateWhere(AccountUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((AccountUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, AccountQuery> implements ASegment<GroupBy> {
    public GroupBy(AccountQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, AccountQuery> implements ASegment<HavingOperator<Having>> {
    public Having(AccountQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, AccountQuery> implements ASegment<OrderByApply<QueryOrderBy, AccountQuery>> {
    public QueryOrderBy(AccountQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, AccountUpdate> implements ASegment<OrderByApply<UpdateOrderBy, AccountUpdate>> {
    public UpdateOrderBy(AccountUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, AccountUpdate> implements ASegment<UpdateApply<UpdateSetter, AccountUpdate>> {
    public UpdateSetter(AccountUpdate updater) {
      super(updater);
    }
  }
}
