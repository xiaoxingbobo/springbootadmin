package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.RoleAuthorityMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.helper.RoleAuthoritySegment;
import java.util.Optional;

/**
 *
 * RoleAuthorityUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class RoleAuthorityUpdate extends BaseUpdate<RoleAuthority, RoleAuthorityUpdate, RoleAuthorityQuery> {
  public final RoleAuthoritySegment.UpdateSetter set = new RoleAuthoritySegment.UpdateSetter(this);

  public final RoleAuthoritySegment.UpdateWhere where = new RoleAuthoritySegment.UpdateWhere(this);

  public final RoleAuthoritySegment.UpdateOrderBy orderBy = new RoleAuthoritySegment.UpdateOrderBy(this);

  public RoleAuthorityUpdate() {
    this(true, null, null, null);
  }

  public RoleAuthorityUpdate(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, RoleAuthority.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public RoleAuthoritySegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static RoleAuthorityUpdate emptyUpdater() {
    return new RoleAuthorityUpdate(false, null, null, null);
  }

  public static RoleAuthorityUpdate emptyUpdater(StringSupplier table) {
    return new RoleAuthorityUpdate(false, fragment(table), null, null);
  }

  public static RoleAuthorityUpdate updater() {
    return new RoleAuthorityUpdate(true, null, null, null);
  }

  public static RoleAuthorityUpdate defaultUpdater() {
    return updater();
  }

  public static RoleAuthorityUpdate updater(StringSupplier table) {
    return new RoleAuthorityUpdate(true, fragment(table), null, null);
  }
}
