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
import com.xxbb.springbootapi.entity.Peoper;
import com.xxbb.springbootapi.mapper.PeoperMapper;
import com.xxbb.springbootapi.wrapper.PeoperQuery;
import com.xxbb.springbootapi.wrapper.PeoperUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * PeoperMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class PeoperMapping extends AMapping<Peoper, PeoperQuery, PeoperUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "peoper";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "Peoper";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<Peoper> id = new FieldMapping<Peoper>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), Peoper::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<Peoper> createTime = new FieldMapping<Peoper>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), Peoper::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  name : name
   */
  public static final FieldMapping<Peoper> name = new FieldMapping<Peoper>
  	("name", "name", null, null, null, Boolean.class, null)
  	.sg((e, v) -> e.setName((Boolean) v), Peoper::getName);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<Peoper> updateTime = new FieldMapping<Peoper>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), Peoper::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<Peoper> isDeleted = new FieldMapping<Peoper>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Boolean.class, null)
  	.sg((e, v) -> e.setIsDeleted((Boolean) v), Peoper::getIsDeleted);

  public static final IFMConfig DEFAULT_SETTER = new IFMConfig(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, name, updateTime, isDeleted));

  public static final PeoperMapping MAPPING = new PeoperMapping();

  protected PeoperMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return Peoper.class;
  }

  @Override
  public Class mapperClass() {
    return PeoperMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new Peoper();
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
  protected final PeoperQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new PeoperQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final PeoperUpdate updater(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new PeoperUpdate(defaults, fragment(table), alias, shared);
  }
}
