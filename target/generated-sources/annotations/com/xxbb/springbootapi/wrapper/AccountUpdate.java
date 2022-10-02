package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.AccountMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.helper.AccountSegment;
import java.util.Optional;

/**
 *
 * AccountUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class AccountUpdate extends BaseUpdate<Account, AccountUpdate, AccountQuery> {
  public final AccountSegment.UpdateSetter set = new AccountSegment.UpdateSetter(this);

  public final AccountSegment.UpdateWhere where = new AccountSegment.UpdateWhere(this);

  public final AccountSegment.UpdateOrderBy orderBy = new AccountSegment.UpdateOrderBy(this);

  public AccountUpdate() {
    this(true, null, null, null);
  }

  public AccountUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Account.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public AccountSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static AccountUpdate emptyUpdater() {
    return new AccountUpdate(false, null, null, null);
  }

  public static AccountUpdate emptyUpdater(StringSupplier table) {
    return new AccountUpdate(false, fragment(table), null, null);
  }

  public static AccountUpdate updater() {
    return new AccountUpdate(true, null, null, null);
  }

  public static AccountUpdate defaultUpdater() {
    return updater();
  }

  public static AccountUpdate updater(StringSupplier table) {
    return new AccountUpdate(true, fragment(table), null, null);
  }
}
