package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.DoopMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.helper.DoopSegment;
import java.util.Optional;

/**
 *
 * DoopUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class DoopUpdate extends BaseUpdate<Doop, DoopUpdate, DoopQuery> {
  public final DoopSegment.UpdateSetter set = new DoopSegment.UpdateSetter(this);

  public final DoopSegment.UpdateWhere where = new DoopSegment.UpdateWhere(this);

  public final DoopSegment.UpdateOrderBy orderBy = new DoopSegment.UpdateOrderBy(this);

  public DoopUpdate() {
    this(true, null, null, null);
  }

  public DoopUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Doop.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public DoopSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static DoopUpdate emptyUpdater() {
    return new DoopUpdate(false, null, null, null);
  }

  public static DoopUpdate emptyUpdater(StringSupplier table) {
    return new DoopUpdate(false, fragment(table), null, null);
  }

  public static DoopUpdate updater() {
    return new DoopUpdate(true, null, null, null);
  }

  public static DoopUpdate defaultUpdater() {
    return updater();
  }

  public static DoopUpdate updater(StringSupplier table) {
    return new DoopUpdate(true, fragment(table), null, null);
  }
}
