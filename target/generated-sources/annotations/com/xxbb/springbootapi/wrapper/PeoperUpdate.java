package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.PeoperMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.helper.PeoperSegment;
import java.util.Optional;

/**
 *
 * PeoperUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class PeoperUpdate extends BaseUpdate<Peoper, PeoperUpdate, PeoperQuery> {
  public final PeoperSegment.UpdateSetter set = new PeoperSegment.UpdateSetter(this);

  public final PeoperSegment.UpdateWhere where = new PeoperSegment.UpdateWhere(this);

  public final PeoperSegment.UpdateOrderBy orderBy = new PeoperSegment.UpdateOrderBy(this);

  public PeoperUpdate() {
    this(true, null, null, null);
  }

  public PeoperUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Peoper.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public PeoperSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static PeoperUpdate emptyUpdater() {
    return new PeoperUpdate(false, null, null, null);
  }

  public static PeoperUpdate emptyUpdater(StringSupplier table) {
    return new PeoperUpdate(false, fragment(table), null, null);
  }

  public static PeoperUpdate updater() {
    return new PeoperUpdate(true, null, null, null);
  }

  public static PeoperUpdate defaultUpdater() {
    return updater();
  }

  public static PeoperUpdate updater(StringSupplier table) {
    return new PeoperUpdate(true, fragment(table), null, null);
  }
}
