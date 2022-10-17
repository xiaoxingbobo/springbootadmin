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
import com.xxbb.springbootapi.entity.Doop;
import com.xxbb.springbootapi.mapper.DoopMapper;
import com.xxbb.springbootapi.wrapper.DoopQuery;
import com.xxbb.springbootapi.wrapper.DoopUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * DoopMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class DoopMapping extends AMapping<Doop, DoopQuery, DoopUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "doop";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "Doop";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<Doop> id = new FieldMapping<Doop>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), Doop::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<Doop> createTime = new FieldMapping<Doop>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), Doop::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  qww : qww
   */
  public static final FieldMapping<Doop> qww = new FieldMapping<Doop>
  	("qww", "qww", null, null, null, String.class, null)
  	.sg((e, v) -> e.setQww((String) v), Doop::getQww);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<Doop> updateTime = new FieldMapping<Doop>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), Doop::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<Doop> isDeleted = new FieldMapping<Doop>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Boolean.class, null)
  	.sg((e, v) -> e.setIsDeleted((Boolean) v), Doop::getIsDeleted);

  public static final IFMConfig DEFAULT_SETTER = new IFMConfig(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, qww, updateTime, isDeleted));

  public static final DoopMapping MAPPING = new DoopMapping();

  protected DoopMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return Doop.class;
  }

  @Override
  public Class mapperClass() {
    return DoopMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new Doop();
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
  protected final DoopQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new DoopQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final DoopUpdate updater(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new DoopUpdate(defaults, fragment(table), alias, shared);
  }
}
