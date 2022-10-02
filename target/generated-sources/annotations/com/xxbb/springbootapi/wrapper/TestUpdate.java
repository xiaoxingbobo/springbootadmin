package com.xxbb.springbootapi.wrapper;

import static cn.org.atool.fluent.mybatis.If.notBlank;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;
import static com.xxbb.springbootapi.helper.TestMapping.MAPPING;

import cn.org.atool.fluent.mybatis.base.crud.BaseUpdate;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.segment.fragment.IFragment;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.helper.TestSegment;
import java.util.Optional;

/**
 *
 * TestUpdate: 更新构造
 *
 * @author powered by FluentMybatis
 */
public class TestUpdate extends BaseUpdate<Test, TestUpdate, TestQuery> {
  public final TestSegment.UpdateSetter set = new TestSegment.UpdateSetter(this);

  public final TestSegment.UpdateWhere where = new TestSegment.UpdateWhere(this);

  public final TestSegment.UpdateOrderBy orderBy = new TestSegment.UpdateOrderBy(this);

  public TestUpdate() {
    this(true, null, null, null);
  }

  public TestUpdate(boolean defaults, IFragment table, StringSupplier alias, Parameters shared) {
    super(table, alias, Test.class);
    if(shared != null) {
      this.sharedParameter(shared);
    }
    if (defaults) {
      MAPPING.defaultSetter().setUpdateDefault(this);
    }
  }

  @Override
  public TestSegment.UpdateWhere where() {
    return this.where;
  }

  @Override
  public Optional<IMapping> mapping() {
    return Optional.of(MAPPING);
  }

  public static TestUpdate emptyUpdater() {
    return new TestUpdate(false, null, null, null);
  }

  public static TestUpdate emptyUpdater(StringSupplier table) {
    return new TestUpdate(false, fragment(table), null, null);
  }

  public static TestUpdate updater() {
    return new TestUpdate(true, null, null, null);
  }

  public static TestUpdate defaultUpdater() {
    return updater();
  }

  public static TestUpdate updater(StringSupplier table) {
    return new TestUpdate(true, fragment(table), null, null);
  }
}
