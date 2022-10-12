package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.GenRecordMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.helper.GenRecordSegment;
import java.util.Optional;

/**
 *
 * GenRecordUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class GenRecordUpdate extends BaseUpdate<GenRecord, GenRecordUpdate, GenRecordQuery> {
  public final GenRecordSegment.UpdateSetter set = new GenRecordSegment.UpdateSetter(this);

  public final GenRecordSegment.UpdateWhere where = new GenRecordSegment.UpdateWhere(this);

  public final GenRecordSegment.UpdateOrderBy orderBy = new GenRecordSegment.UpdateOrderBy(this);

  public GenRecordUpdate() {
    this(true, null, null, null);
  }

  public GenRecordUpdate(boolean defaults, IFragment table, StringSupplier alias,
      Parameters shared) {
    super(table, alias, GenRecord.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public GenRecordSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static GenRecordUpdate emptyUpdater() {
    return new GenRecordUpdate(false, null, null, null);
  }

  public static GenRecordUpdate emptyUpdater(StringSupplier table) {
    return new GenRecordUpdate(false, fragment(table), null, null);
  }

  public static GenRecordUpdate updater() {
    return new GenRecordUpdate(true, null, null, null);
  }

  public static GenRecordUpdate defaultUpdater() {
    return updater();
  }

  public static GenRecordUpdate updater(StringSupplier table) {
    return new GenRecordUpdate(true, fragment(table), null, null);
  }
}
