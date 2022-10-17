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
import com.xxbb.springbootapi.mapper.nameMapper;
import com.xxbb.springbootapi.wrapper.nameQuery;
import com.xxbb.springbootapi.wrapper.nameUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * nameMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class nameMapping extends AMapping<name, nameQuery, nameUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "name";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "name";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<name> id = new FieldMapping<name>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), name::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<name> createTime = new FieldMapping<name>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), name::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<name> isDeleted = new FieldMapping<name>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Integer.class, null)
  	.sg((e, v) -> e.setIsDeleted((Integer) v), name::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<name> updateTime = new FieldMapping<name>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), name::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  测试name : 测试name
   */
  public static final FieldMapping<name> 测试name = new FieldMapping<name>
  	("测试name", "测试name", null, null, null, Boolean.class, null)
  	.sg((e, v) -> e.set测试name((Boolean) v), name::get测试name);

  public static final <error> DEFAULT_SETTER = new <error>(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, isDeleted, updateTime, 测试name));

  public static final nameMapping MAPPING = new nameMapping();

  protected nameMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return name.class;
  }

  @Override
  public Class mapperClass() {
    return nameMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new name();
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
  protected final nameQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new nameQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final nameUpdate updater(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new nameUpdate(defaults, fragment(table), alias, shared);
  }
}
