package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.EngineeringCostMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.EngineeringCost;
import com.xxbb.springbootapi.helper.EngineeringCostSegment;
import java.util.Optional;

/**
 *
 * EngineeringCostUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class EngineeringCostUpdate extends BaseUpdate<EngineeringCost, EngineeringCostUpdate, EngineeringCostQuery> {
  public final EngineeringCostSegment.UpdateSetter set = new EngineeringCostSegment.UpdateSetter(this);

  public final EngineeringCostSegment.UpdateWhere where = new EngineeringCostSegment.UpdateWhere(this);

  public final EngineeringCostSegment.UpdateOrderBy orderBy = new EngineeringCostSegment.UpdateOrderBy(this);

  public EngineeringCostUpdate() {
    this(true, null, null, null);
  }

  public EngineeringCostUpdate(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, EngineeringCost.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public EngineeringCostSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static EngineeringCostUpdate emptyUpdater() {
    return new EngineeringCostUpdate(false, null, null, null);
  }

  public static EngineeringCostUpdate emptyUpdater(StringSupplier table) {
    return new EngineeringCostUpdate(false, fragment(table), null, null);
  }

  public static EngineeringCostUpdate updater() {
    return new EngineeringCostUpdate(true, null, null, null);
  }

  public static EngineeringCostUpdate defaultUpdater() {
    return updater();
  }

  public static EngineeringCostUpdate updater(StringSupplier table) {
    return new EngineeringCostUpdate(true, fragment(table), null, null);
  }
}
