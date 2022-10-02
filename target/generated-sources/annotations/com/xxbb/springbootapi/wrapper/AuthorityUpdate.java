package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.AuthorityMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.helper.AuthoritySegment;
import java.util.Optional;

/**
 *
 * AuthorityUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class AuthorityUpdate extends BaseUpdate<Authority, AuthorityUpdate, AuthorityQuery> {
  public final AuthoritySegment.UpdateSetter set = new AuthoritySegment.UpdateSetter(this);

  public final AuthoritySegment.UpdateWhere where = new AuthoritySegment.UpdateWhere(this);

  public final AuthoritySegment.UpdateOrderBy orderBy = new AuthoritySegment.UpdateOrderBy(this);

  public AuthorityUpdate() {
    this(true, null, null, null);
  }

  public AuthorityUpdate(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, Authority.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public AuthoritySegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static AuthorityUpdate emptyUpdater() {
    return new AuthorityUpdate(false, null, null, null);
  }

  public static AuthorityUpdate emptyUpdater(StringSupplier table) {
    return new AuthorityUpdate(false, fragment(table), null, null);
  }

  public static AuthorityUpdate updater() {
    return new AuthorityUpdate(true, null, null, null);
  }

  public static AuthorityUpdate defaultUpdater() {
    return updater();
  }

  public static AuthorityUpdate updater(StringSupplier table) {
    return new AuthorityUpdate(true, fragment(table), null, null);
  }
}
