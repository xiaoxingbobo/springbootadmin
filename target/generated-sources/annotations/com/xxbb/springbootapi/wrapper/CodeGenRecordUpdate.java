package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.CodeGenRecordMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.helper.CodeGenRecordSegment;
import java.util.Optional;

/**
 *
 * CodeGenRecordUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class CodeGenRecordUpdate extends BaseUpdate<GenRecord, CodeGenRecordUpdate, CodeGenRecordQuery> {
  public final CodeGenRecordSegment.UpdateSetter set = new CodeGenRecordSegment.UpdateSetter(this);

  public final CodeGenRecordSegment.UpdateWhere where = new CodeGenRecordSegment.UpdateWhere(this);

  public final CodeGenRecordSegment.UpdateOrderBy orderBy = new CodeGenRecordSegment.UpdateOrderBy(this);

  public CodeGenRecordUpdate() {
    this(true, null, null, null);
  }

  public CodeGenRecordUpdate(boolean defaults, IFragment table, StringSupplier alias,
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
  public CodeGenRecordSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static CodeGenRecordUpdate emptyUpdater() {
    return new CodeGenRecordUpdate(false, null, null, null);
  }

  public static CodeGenRecordUpdate emptyUpdater(StringSupplier table) {
    return new CodeGenRecordUpdate(false, fragment(table), null, null);
  }

  public static CodeGenRecordUpdate updater() {
    return new CodeGenRecordUpdate(true, null, null, null);
  }

  public static CodeGenRecordUpdate defaultUpdater() {
    return updater();
  }

  public static CodeGenRecordUpdate updater(StringSupplier table) {
    return new CodeGenRecordUpdate(true, fragment(table), null, null);
  }
}
