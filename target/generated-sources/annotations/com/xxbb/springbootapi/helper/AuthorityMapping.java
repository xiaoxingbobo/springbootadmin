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
import com.xxbb.springbootapi.entity.Authority;
import com.xxbb.springbootapi.mapper.AuthorityMapper;
import com.xxbb.springbootapi.wrapper.AuthorityQuery;
import com.xxbb.springbootapi.wrapper.AuthorityUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * AuthorityMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class AuthorityMapping extends AMapping<Authority, AuthorityQuery, AuthorityUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "authority";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "Authority";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<Authority> id = new FieldMapping<Authority>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), Authority::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<Authority> createTime = new FieldMapping<Authority>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), Authority::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  name : name
   */
  public static final FieldMapping<Authority> name = new FieldMapping<Authority>
  	("name", "name", null, null, null, String.class, null)
  	.sg((e, v) -> e.setName((String) v), Authority::getName);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<Authority> updateTime = new FieldMapping<Authority>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), Authority::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  value : value
   */
  public static final FieldMapping<Authority> value = new FieldMapping<Authority>
  	("value", "value", null, null, null, String.class, null)
  	.sg((e, v) -> e.setValue((String) v), Authority::getValue);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<Authority> isDeleted = new FieldMapping<Authority>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Boolean.class, null)
  	.sg((e, v) -> e.setIsDeleted((Boolean) v), Authority::getIsDeleted);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, name, updateTime, value, isDeleted));

  public static final AuthorityMapping MAPPING = new AuthorityMapping();

  protected AuthorityMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return Authority.class;
  }

  @Override
  public Class mapperClass() {
    return AuthorityMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new Authority();
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
  protected final AuthorityQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new AuthorityQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final AuthorityUpdate updater(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new AuthorityUpdate(defaults, fragment(table), alias, shared);
  }
}
