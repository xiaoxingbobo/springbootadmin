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
import com.xxbb.springbootapi.entity.GenRecord;
import com.xxbb.springbootapi.mapper.GenRecordMapper;
import com.xxbb.springbootapi.wrapper.GenRecordQuery;
import com.xxbb.springbootapi.wrapper.GenRecordUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * GenRecordMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class GenRecordMapping extends AMapping<GenRecord, GenRecordQuery, GenRecordUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "gen_record";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "GenRecord";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<GenRecord> id = new FieldMapping<GenRecord>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), GenRecord::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<GenRecord> createTime = new FieldMapping<GenRecord>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), GenRecord::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  entityName : entity_name
   */
  public static final FieldMapping<GenRecord> entityName = new FieldMapping<GenRecord>
  	("entityName", "entity_name", null, null, null, String.class, null)
  	.sg((e, v) -> e.setEntityName((String) v), GenRecord::getEntityName);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<GenRecord> updateTime = new FieldMapping<GenRecord>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), GenRecord::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<GenRecord> isDeleted = new FieldMapping<GenRecord>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Boolean.class, null)
  	.sg((e, v) -> e.setIsDeleted((Boolean) v), GenRecord::getIsDeleted);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, entityName, updateTime, isDeleted));

  public static final GenRecordMapping MAPPING = new GenRecordMapping();

  protected GenRecordMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return GenRecord.class;
  }

  @Override
  public Class mapperClass() {
    return GenRecordMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new GenRecord();
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
  protected final GenRecordQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new GenRecordQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final GenRecordUpdate updater(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new GenRecordUpdate(defaults, fragment(table), alias, shared);
  }
}
