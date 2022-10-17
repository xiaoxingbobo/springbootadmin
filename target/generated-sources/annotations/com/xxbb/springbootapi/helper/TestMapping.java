package com.xxbb.springbootapi.helper;

import static cn.org.atool.fluent.mybatis.base.model.UniqueType.*;
import static cn.org.atool.fluent.mybatis.segment.fragment.Fragments.fragment;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;
import cn.org.atool.fluent.mybatis.base.entity.AMapping;
import cn.org.atool.fluent.mybatis.base.entity.TableId;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.functions.StringSupplier;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.xxbb.springbootapi.config.IFMConfig;
import com.xxbb.springbootapi.entity.Test;
import com.xxbb.springbootapi.mapper.TestMapper;
import com.xxbb.springbootapi.wrapper.TestQuery;
import com.xxbb.springbootapi.wrapper.TestUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * TestMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class TestMapping extends AMapping<Test, TestQuery, TestUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "test";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "Test";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<Test> id = new FieldMapping<Test>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), Test::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  age : age
   */
  public static final FieldMapping<Test> age = new FieldMapping<Test>
  	("age", "age", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setAge((Integer) v), Test::getAge);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<Test> createTime = new FieldMapping<Test>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), Test::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  name : name
   */
  public static final FieldMapping<Test> name = new FieldMapping<Test>
  	("name", "name", null, null, null, String.class, null)
  	.sg((e, v) -> e.setName((String) v), Test::getName);

  /**
   * 实体属性 : 数据库字段 映射
   *  sex : sex
   */
  public static final FieldMapping<Test> sex = new FieldMapping<Test>
  	("sex", "sex", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setSex((Integer) v), Test::getSex);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<Test> updateTime = new FieldMapping<Test>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), Test::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<Test> isDeleted = new FieldMapping<Test>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Boolean.class, null)
  	.sg((e, v) -> e.setIsDeleted((Boolean) v), Test::getIsDeleted);

  public static final IFMConfig DEFAULT_SETTER = new IFMConfig(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, age, createTime, name, sex, updateTime, isDeleted));

  public static final TestMapping MAPPING = new TestMapping();

  protected TestMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return Test.class;
  }

  @Override
  public Class mapperClass() {
    return TestMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new Test();
  }

  @Override
  public final List<FieldMapping> allFields() {
    return ALL_FIELD_MAPPING;
  }

  @Override
  public IDefaultSetter defaultSetter() {
    return DEFAULT_SETTER;
  }

  @Override
  protected final TestQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new TestQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final TestUpdate updater(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new TestUpdate(defaults, fragment(table), alias, shared);
  }
}
