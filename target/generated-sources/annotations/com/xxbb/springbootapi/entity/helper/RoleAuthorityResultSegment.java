package com.xxbb.springbootapi.entity.helper;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.assertNotNull;
import static com.xxbb.springbootapi.entity.helper.RoleAuthorityResultMapping.*;

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
import com.xxbb.springbootapi.entity.wrapper.RoleAuthorityResultQuery;
import com.xxbb.springbootapi.entity.wrapper.RoleAuthorityResultUpdate;

/**
 *
 * RoleAuthorityResultSegment
 *
 * @author powered by FluentMybatis
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public interface RoleAuthorityResultSegment {
  interface ASegment<R> {
    R set(FieldMapping fieldMapping);

    default R id() {
      return this.set(id);
    }

    default R authorityId() {
      return this.set(authorityId);
    }

    default R authorityName() {
      return this.set(authorityName);
    }

    default R authorityValue() {
      return this.set(authorityValue);
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
  final class Selector extends SelectorBase<Selector, RoleAuthorityResultQuery> implements ASegment<Selector> {
    public Selector(RoleAuthorityResultQuery query) {
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

    public Selector authorityName(String _alias_) {
      return this.process(authorityName, _alias_);
    }

    public Selector authorityValue(String _alias_) {
      return this.process(authorityValue, _alias_);
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
  abstract class EntityWhere<W extends WhereBase<W, U, RoleAuthorityResultQuery>, U extends IWrapper<?, U, RoleAuthorityResultQuery>> extends WhereBase<W, U, RoleAuthorityResultQuery> {
    public EntityWhere(U wrapper) {
      super(wrapper);
    }

    protected EntityWhere(U wrapper, W where) {
      super(wrapper, where);
    }

    public NumericWhere<W, RoleAuthorityResultQuery> id() {
      return this.set(id);
    }

    public NumericWhere<W, RoleAuthorityResultQuery> authorityId() {
      return this.set(authorityId);
    }

    public StringWhere<W, RoleAuthorityResultQuery> authorityName() {
      return this.set(authorityName);
    }

    public StringWhere<W, RoleAuthorityResultQuery> authorityValue() {
      return this.set(authorityValue);
    }

    public ObjectWhere<W, RoleAuthorityResultQuery> createTime() {
      return this.set(createTime);
    }

    public BooleanWhere<W, RoleAuthorityResultQuery> isDeleted() {
      return this.set(isDeleted);
    }

    public NumericWhere<W, RoleAuthorityResultQuery> roleId() {
      return this.set(roleId);
    }

    public ObjectWhere<W, RoleAuthorityResultQuery> updateTime() {
      return this.set(updateTime);
    }
  }

  /**
   * query where条件设置
   */
  class QueryWhere extends EntityWhere<QueryWhere, RoleAuthorityResultQuery> {
    public QueryWhere(RoleAuthorityResultQuery query) {
      super(query);
    }

    private QueryWhere(RoleAuthorityResultQuery query, QueryWhere where) {
      super(query, where);
    }

    @Override
    protected QueryWhere buildOr(QueryWhere and) {
      return new QueryWhere((RoleAuthorityResultQuery) this.wrapper, and);
    }
  }

  /**
   * update where条件设置
   */
  class UpdateWhere extends EntityWhere<UpdateWhere, RoleAuthorityResultUpdate> {
    public UpdateWhere(RoleAuthorityResultUpdate updater) {
      super(updater);
    }

    private UpdateWhere(RoleAuthorityResultUpdate updater, UpdateWhere where) {
      super(updater, where);
    }

    @Override
    protected UpdateWhere buildOr(UpdateWhere and) {
      return new UpdateWhere((RoleAuthorityResultUpdate) this.wrapper, and);
    }
  }

  /**
   * 分组设置
   */
  final class GroupBy extends GroupByBase<GroupBy, RoleAuthorityResultQuery> implements ASegment<GroupBy> {
    public GroupBy(RoleAuthorityResultQuery query) {
      super(query);
    }
  }

  /**
   * 分组Having条件设置
   */
  final class Having extends HavingBase<Having, RoleAuthorityResultQuery> implements ASegment<HavingOperator<Having>> {
    public Having(RoleAuthorityResultQuery query) {
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
  final class QueryOrderBy extends OrderByBase<QueryOrderBy, RoleAuthorityResultQuery> implements ASegment<OrderByApply<QueryOrderBy, RoleAuthorityResultQuery>> {
    public QueryOrderBy(RoleAuthorityResultQuery query) {
      super(query);
    }
  }

  /**
   * Update OrderBy设置
   */
  final class UpdateOrderBy extends OrderByBase<UpdateOrderBy, RoleAuthorityResultUpdate> implements ASegment<OrderByApply<UpdateOrderBy, RoleAuthorityResultUpdate>> {
    public UpdateOrderBy(RoleAuthorityResultUpdate updater) {
      super(updater);
    }
  }

  /**
   * Update set 设置
   */
  final class UpdateSetter extends UpdateBase<UpdateSetter, RoleAuthorityResultUpdate> implements ASegment<UpdateApply<UpdateSetter, RoleAuthorityResultUpdate>> {
    public UpdateSetter(RoleAuthorityResultUpdate updater) {
      super(updater);
    }
  }
}
