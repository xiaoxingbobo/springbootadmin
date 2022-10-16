package com.xxbb.springbootapi.entity.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.entity.helper.RoleAuthorityResultMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.entity.helper.RoleAuthorityResultSegment;
import java.util.Optional;

/**
 *
 * RoleAuthorityResultUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class RoleAuthorityResultUpdate extends BaseUpdate<RoleAuthorityResult, RoleAuthorityResultUpdate, RoleAuthorityResultQuery> {
  public final RoleAuthorityResultSegment.UpdateSetter set = new RoleAuthorityResultSegment.UpdateSetter(this);

  public final RoleAuthorityResultSegment.UpdateWhere where = new RoleAuthorityResultSegment.UpdateWhere(this);

  public final RoleAuthorityResultSegment.UpdateOrderBy orderBy = new RoleAuthorityResultSegment.UpdateOrderBy(this);

  public RoleAuthorityResultUpdate() {
    this(true, null, null, null);
  }

  public RoleAuthorityResultUpdate(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, RoleAuthorityResult.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public RoleAuthorityResultSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static RoleAuthorityResultUpdate emptyUpdater() {
    return new RoleAuthorityResultUpdate(false, null, null, null);
  }

  public static RoleAuthorityResultUpdate emptyUpdater(StringSupplier table) {
    return new RoleAuthorityResultUpdate(false, fragment(table), null, null);
  }

  public static RoleAuthorityResultUpdate updater() {
    return new RoleAuthorityResultUpdate(true, null, null, null);
  }

  public static RoleAuthorityResultUpdate defaultUpdater() {
    return updater();
  }

  public static RoleAuthorityResultUpdate updater(StringSupplier table) {
    return new RoleAuthorityResultUpdate(true, fragment(table), null, null);
  }
}
