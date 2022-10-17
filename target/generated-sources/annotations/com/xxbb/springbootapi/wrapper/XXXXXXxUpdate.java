package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.XXXXXXxMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.helper.XXXXXXxSegment;
import java.util.Optional;

/**
 *
 * XXXXXXxUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class XXXXXXxUpdate extends BaseUpdate<XXXXXXx, XXXXXXxUpdate, XXXXXXxQuery> {
  public final XXXXXXxSegment.UpdateSetter set = new XXXXXXxSegment.UpdateSetter(this);

  public final XXXXXXxSegment.UpdateWhere where = new XXXXXXxSegment.UpdateWhere(this);

  public final XXXXXXxSegment.UpdateOrderBy orderBy = new XXXXXXxSegment.UpdateOrderBy(this);

  public XXXXXXxUpdate() {
    this(true, null, null, null);
  }

  public XXXXXXxUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, XXXXXXx.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public XXXXXXxSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static XXXXXXxUpdate emptyUpdater() {
    return new XXXXXXxUpdate(false, null, null, null);
  }

  public static XXXXXXxUpdate emptyUpdater(StringSupplier table) {
    return new XXXXXXxUpdate(false, fragment(table), null, null);
  }

  public static XXXXXXxUpdate updater() {
    return new XXXXXXxUpdate(true, null, null, null);
  }

  public static XXXXXXxUpdate defaultUpdater() {
    return updater();
  }

  public static XXXXXXxUpdate updater(StringSupplier table) {
    return new XXXXXXxUpdate(true, fragment(table), null, null);
  }
}
