package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.PeopleMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.People;
import com.xxbb.springbootapi.helper.PeopleSegment;
import java.util.Optional;

/**
 *
 * PeopleUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class PeopleUpdate extends BaseUpdate<People, PeopleUpdate, PeopleQuery> {
  public final PeopleSegment.UpdateSetter set = new PeopleSegment.UpdateSetter(this);

  public final PeopleSegment.UpdateWhere where = new PeopleSegment.UpdateWhere(this);

  public final PeopleSegment.UpdateOrderBy orderBy = new PeopleSegment.UpdateOrderBy(this);

  public PeopleUpdate() {
    this(true, null, null, null);
  }

  public PeopleUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, People.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public PeopleSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static PeopleUpdate emptyUpdater() {
    return new PeopleUpdate(false, null, null, null);
  }

  public static PeopleUpdate emptyUpdater(StringSupplier table) {
    return new PeopleUpdate(false, fragment(table), null, null);
  }

  public static PeopleUpdate updater() {
    return new PeopleUpdate(true, null, null, null);
  }

  public static PeopleUpdate defaultUpdater() {
    return updater();
  }

  public static PeopleUpdate updater(StringSupplier table) {
    return new PeopleUpdate(true, fragment(table), null, null);
  }
}
