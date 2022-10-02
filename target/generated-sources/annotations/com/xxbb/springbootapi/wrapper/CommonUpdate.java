package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.CommonMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Common;
import com.xxbb.springbootapi.helper.CommonSegment;
import java.util.Optional;

/**
 *
 * CommonUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class CommonUpdate extends BaseUpdate<Common, CommonUpdate, CommonQuery> {
  public final CommonSegment.UpdateSetter set = new CommonSegment.UpdateSetter(this);

  public final CommonSegment.UpdateWhere where = new CommonSegment.UpdateWhere(this);

  public final CommonSegment.UpdateOrderBy orderBy = new CommonSegment.UpdateOrderBy(this);

  public CommonUpdate() {
    this(true, null, null, null);
  }

  public CommonUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Common.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public CommonSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static CommonUpdate emptyUpdater() {
    return new CommonUpdate(false, null, null, null);
  }

  public static CommonUpdate emptyUpdater(StringSupplier table) {
    return new CommonUpdate(false, fragment(table), null, null);
  }

  public static CommonUpdate updater() {
    return new CommonUpdate(true, null, null, null);
  }

  public static CommonUpdate defaultUpdater() {
    return updater();
  }

  public static CommonUpdate updater(StringSupplier table) {
    return new CommonUpdate(true, fragment(table), null, null);
  }
}
