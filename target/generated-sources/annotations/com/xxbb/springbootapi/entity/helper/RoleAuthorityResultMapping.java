package com.xxbb.springbootapi.entity.helper;

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
import com.xxbb.springbootapi.entity.dto.RoleAuthorityResult;
import com.xxbb.springbootapi.entity.mapper.RoleAuthorityResultMapper;
import com.xxbb.springbootapi.entity.wrapper.RoleAuthorityResultQuery;
import com.xxbb.springbootapi.entity.wrapper.RoleAuthorityResultUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * RoleAuthorityResultMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class RoleAuthorityResultMapping extends AMapping<RoleAuthorityResult, RoleAuthorityResultQuery, RoleAuthorityResultUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "role_authority_result";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "RoleAuthorityResult";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<RoleAuthorityResult> id = new FieldMapping<RoleAuthorityResult>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), RoleAuthorityResult::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  authorityId : authority_id
   */
  public static final FieldMapping<RoleAuthorityResult> authorityId = new FieldMapping<RoleAuthorityResult>
  	("authorityId", "authority_id", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setAuthorityId((Integer) v), RoleAuthorityResult::getAuthorityId);

  /**
   * 实体属性 : 数据库字段 映射
   *  authorityName : authority_name
   */
  public static final FieldMapping<RoleAuthorityResult> authorityName = new FieldMapping<RoleAuthorityResult>
  	("authorityName", "authority_name", null, null, null, String.class, null)
  	.sg((e, v) -> e.setAuthorityName((String) v), RoleAuthorityResult::getAuthorityName);

  /**
   * 实体属性 : 数据库字段 映射
   *  authorityValue : authority_value
   */
  public static final FieldMapping<RoleAuthorityResult> authorityValue = new FieldMapping<RoleAuthorityResult>
  	("authorityValue", "authority_value", null, null, null, String.class, null)
  	.sg((e, v) -> e.setAuthorityValue((String) v), RoleAuthorityResult::getAuthorityValue);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<RoleAuthorityResult> createTime = new FieldMapping<RoleAuthorityResult>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), RoleAuthorityResult::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<RoleAuthorityResult> isDeleted = new FieldMapping<RoleAuthorityResult>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Boolean.class, null)
  	.sg((e, v) -> e.setIsDeleted((Boolean) v), RoleAuthorityResult::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  roleId : role_id
   */
  public static final FieldMapping<RoleAuthorityResult> roleId = new FieldMapping<RoleAuthorityResult>
  	("roleId", "role_id", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setRoleId((Integer) v), RoleAuthorityResult::getRoleId);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<RoleAuthorityResult> updateTime = new FieldMapping<RoleAuthorityResult>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), RoleAuthorityResult::getUpdateTime);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, authorityId, authorityName, authorityValue, createTime, isDeleted, roleId, updateTime));

  public static final RoleAuthorityResultMapping MAPPING = new RoleAuthorityResultMapping();

  protected RoleAuthorityResultMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return RoleAuthorityResult.class;
  }

  @Override
  public Class mapperClass() {
    return RoleAuthorityResultMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new RoleAuthorityResult();
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
  protected final RoleAuthorityResultQuery query(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new RoleAuthorityResultQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final RoleAuthorityResultUpdate updater(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new RoleAuthorityResultUpdate(defaults, fragment(table), alias, shared);
  }
}
