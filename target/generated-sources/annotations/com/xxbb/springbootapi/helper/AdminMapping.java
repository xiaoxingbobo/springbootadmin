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
import com.xxbb.springbootapi.entity.Admin;
import com.xxbb.springbootapi.mapper.AdminMapper;
import com.xxbb.springbootapi.wrapper.AdminQuery;
import com.xxbb.springbootapi.wrapper.AdminUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * AdminMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class AdminMapping extends AMapping<Admin, AdminQuery, AdminUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "admin";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "Admin";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<Admin> id = new FieldMapping<Admin>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), Admin::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<Admin> createTime = new FieldMapping<Admin>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), Admin::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<Admin> isDeleted = new FieldMapping<Admin>
  	("isDeleted", "is_deleted", null, "0", null, Integer.class, null)
  	.sg((e, v) -> e.setIsDeleted((Integer) v), Admin::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<Admin> updateTime = new FieldMapping<Admin>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), Admin::getUpdateTime);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, isDeleted, updateTime));

  public static final AdminMapping MAPPING = new AdminMapping();

  protected AdminMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return Admin.class;
  }

  @Override
  public Class mapperClass() {
    return AdminMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new Admin();
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
  protected final AdminQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new AdminQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final AdminUpdate updater(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new AdminUpdate(defaults, fragment(table), alias, shared);
  }
}
