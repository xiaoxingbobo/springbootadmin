package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.nameMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.helper.nameSegment;
import java.util.Optional;

/**
 *
 * nameUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class nameUpdate extends BaseUpdate<name, nameUpdate, nameQuery> {
  public final nameSegment.UpdateSetter set = new nameSegment.UpdateSetter(this);

  public final nameSegment.UpdateWhere where = new nameSegment.UpdateWhere(this);

  public final nameSegment.UpdateOrderBy orderBy = new nameSegment.UpdateOrderBy(this);

  public nameUpdate() {
    this(true, null, null, null);
  }

  public nameUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, name.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public nameSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static nameUpdate emptyUpdater() {
    return new nameUpdate(false, null, null, null);
  }

  public static nameUpdate emptyUpdater(StringSupplier table) {
    return new nameUpdate(false, fragment(table), null, null);
  }

  public static nameUpdate updater() {
    return new nameUpdate(true, null, null, null);
  }

  public static nameUpdate defaultUpdater() {
    return updater();
  }

  public static nameUpdate updater(StringSupplier table) {
    return new nameUpdate(true, fragment(table), null, null);
  }
}
