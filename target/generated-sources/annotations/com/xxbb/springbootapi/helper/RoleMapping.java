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
import com.xxbb.springbootapi.entity.Role;
import com.xxbb.springbootapi.mapper.RoleMapper;
import com.xxbb.springbootapi.wrapper.RoleQuery;
import com.xxbb.springbootapi.wrapper.RoleUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * RoleMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class RoleMapping extends AMapping<Role, RoleQuery, RoleUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "role";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "Role";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<Role> id = new FieldMapping<Role>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), Role::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<Role> createTime = new FieldMapping<Role>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), Role::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  name : name
   */
  public static final FieldMapping<Role> name = new FieldMapping<Role>
  	("name", "name", null, null, null, String.class, null)
  	.sg((e, v) -> e.setName((String) v), Role::getName);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<Role> updateTime = new FieldMapping<Role>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), Role::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<Role> isDeleted = new FieldMapping<Role>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Boolean.class, null)
  	.sg((e, v) -> e.setIsDeleted((Boolean) v), Role::getIsDeleted);

  public static final IFMConfig DEFAULT_SETTER = new IFMConfig(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, name, updateTime, isDeleted));

  public static final RoleMapping MAPPING = new RoleMapping();

  protected RoleMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return Role.class;
  }

  @Override
  public Class mapperClass() {
    return RoleMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new Role();
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
  protected final RoleQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new RoleQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final RoleUpdate updater(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new RoleUpdate(defaults, fragment(table), alias, shared);
  }
}
