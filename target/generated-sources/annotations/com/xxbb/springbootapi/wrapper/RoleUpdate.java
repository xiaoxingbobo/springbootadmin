package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.RoleMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.helper.RoleSegment;
import java.util.Optional;

/**
 *
 * RoleUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class RoleUpdate extends BaseUpdate<Role, RoleUpdate, RoleQuery> {
  public final RoleSegment.UpdateSetter set = new RoleSegment.UpdateSetter(this);

  public final RoleSegment.UpdateWhere where = new RoleSegment.UpdateWhere(this);

  public final RoleSegment.UpdateOrderBy orderBy = new RoleSegment.UpdateOrderBy(this);

  public RoleUpdate() {
    this(true, null, null, null);
  }

  public RoleUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Role.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public RoleSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static RoleUpdate emptyUpdater() {
    return new RoleUpdate(false, null, null, null);
  }

  public static RoleUpdate emptyUpdater(StringSupplier table) {
    return new RoleUpdate(false, fragment(table), null, null);
  }

  public static RoleUpdate updater() {
    return new RoleUpdate(true, null, null, null);
  }

  public static RoleUpdate defaultUpdater() {
    return updater();
  }

  public static RoleUpdate updater(StringSupplier table) {
    return new RoleUpdate(true, fragment(table), null, null);
  }
}
