package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.HaMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Ha;
import com.xxbb.springbootapi.helper.HaSegment;
import java.util.Optional;

/**
 *
 * HaUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class HaUpdate extends BaseUpdate<Ha, HaUpdate, HaQuery> {
  public final HaSegment.UpdateSetter set = new HaSegment.UpdateSetter(this);

  public final HaSegment.UpdateWhere where = new HaSegment.UpdateWhere(this);

  public final HaSegment.UpdateOrderBy orderBy = new HaSegment.UpdateOrderBy(this);

  public HaUpdate() {
    this(true, null, null, null);
  }

  public HaUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Ha.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public HaSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static HaUpdate emptyUpdater() {
    return new HaUpdate(false, null, null, null);
  }

  public static HaUpdate emptyUpdater(StringSupplier table) {
    return new HaUpdate(false, fragment(table), null, null);
  }

  public static HaUpdate updater() {
    return new HaUpdate(true, null, null, null);
  }

  public static HaUpdate defaultUpdater() {
    return updater();
  }

  public static HaUpdate updater(StringSupplier table) {
    return new HaUpdate(true, fragment(table), null, null);
  }
}
