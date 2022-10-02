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
import com.xxbb.springbootapi.entity.RoleAuthority;
import com.xxbb.springbootapi.mapper.RoleAuthorityMapper;
import com.xxbb.springbootapi.wrapper.RoleAuthorityQuery;
import com.xxbb.springbootapi.wrapper.RoleAuthorityUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * RoleAuthorityMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class RoleAuthorityMapping extends AMapping<RoleAuthority, RoleAuthorityQuery, RoleAuthorityUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "role_authority";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "RoleAuthority";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<RoleAuthority> id = new FieldMapping<RoleAuthority>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), RoleAuthority::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  authorityId : authority_id
   */
  public static final FieldMapping<RoleAuthority> authorityId = new FieldMapping<RoleAuthority>
  	("authorityId", "authority_id", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setAuthorityId((Integer) v), RoleAuthority::getAuthorityId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<RoleAuthority> createTime = new FieldMapping<RoleAuthority>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), RoleAuthority::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<RoleAuthority> isDeleted = new FieldMapping<RoleAuthority>
  	("isDeleted", "is_deleted", null, "0", null, Integer.class, null)
  	.sg((e, v) -> e.setIsDeleted((Integer) v), RoleAuthority::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  roleId : role_id
   */
  public static final FieldMapping<RoleAuthority> roleId = new FieldMapping<RoleAuthority>
  	("roleId", "role_id", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setRoleId((Integer) v), RoleAuthority::getRoleId);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<RoleAuthority> updateTime = new FieldMapping<RoleAuthority>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), RoleAuthority::getUpdateTime);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, authorityId, createTime, isDeleted, roleId, updateTime));

  public static final RoleAuthorityMapping MAPPING = new RoleAuthorityMapping();

  protected RoleAuthorityMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return RoleAuthority.class;
  }

  @Override
  public Class mapperClass() {
    return RoleAuthorityMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new RoleAuthority();
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
  protected final RoleAuthorityQuery query(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new RoleAuthorityQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final RoleAuthorityUpdate updater(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new RoleAuthorityUpdate(defaults, fragment(table), alias, shared);
  }
}
