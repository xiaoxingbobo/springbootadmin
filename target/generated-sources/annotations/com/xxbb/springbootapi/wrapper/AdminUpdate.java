package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.AdminMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.helper.AdminSegment;
import java.util.Optional;

/**
 *
 * AdminUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class AdminUpdate extends BaseUpdate<Admin, AdminUpdate, AdminQuery> {
  public final AdminSegment.UpdateSetter set = new AdminSegment.UpdateSetter(this);

  public final AdminSegment.UpdateWhere where = new AdminSegment.UpdateWhere(this);

  public final AdminSegment.UpdateOrderBy orderBy = new AdminSegment.UpdateOrderBy(this);

  public AdminUpdate() {
    this(true, null, null, null);
  }

  public AdminUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Admin.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public AdminSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static AdminUpdate emptyUpdater() {
    return new AdminUpdate(false, null, null, null);
  }

  public static AdminUpdate emptyUpdater(StringSupplier table) {
    return new AdminUpdate(false, fragment(table), null, null);
  }

  public static AdminUpdate updater() {
    return new AdminUpdate(true, null, null, null);
  }

  public static AdminUpdate defaultUpdater() {
    return updater();
  }

  public static AdminUpdate updater(StringSupplier table) {
    return new AdminUpdate(true, fragment(table), null, null);
  }
}
