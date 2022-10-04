package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.UserMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.helper.UserSegment;
import java.util.Optional;

/**
 *
 * UserUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class UserUpdate extends BaseUpdate<User, UserUpdate, UserQuery> {
  public final UserSegment.UpdateSetter set = new UserSegment.UpdateSetter(this);

  public final UserSegment.UpdateWhere where = new UserSegment.UpdateWhere(this);

  public final UserSegment.UpdateOrderBy orderBy = new UserSegment.UpdateOrderBy(this);

  public UserUpdate() {
    this(true, null, null, null);
  }

  public UserUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, User.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public UserSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static UserUpdate emptyUpdater() {
    return new UserUpdate(false, null, null, null);
  }

  public static UserUpdate emptyUpdater(StringSupplier table) {
    return new UserUpdate(false, fragment(table), null, null);
  }

  public static UserUpdate updater() {
    return new UserUpdate(true, null, null, null);
  }

  public static UserUpdate defaultUpdater() {
    return updater();
  }

  public static UserUpdate updater(StringSupplier table) {
    return new UserUpdate(true, fragment(table), null, null);
  }
}
