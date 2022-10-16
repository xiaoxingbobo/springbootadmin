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
import com.xxbb.springbootapi.entity.Ha;
import com.xxbb.springbootapi.mapper.HaMapper;
import com.xxbb.springbootapi.wrapper.HaQuery;
import com.xxbb.springbootapi.wrapper.HaUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * HaMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class HaMapping extends AMapping<Ha, HaQuery, HaUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "ha";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "Ha";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<Ha> id = new FieldMapping<Ha>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), Ha::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<Ha> createTime = new FieldMapping<Ha>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), Ha::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<Ha> isDeleted = new FieldMapping<Ha>
  	("isDeleted", "is_deleted", null, "0", null, Integer.class, null)
  	.sg((e, v) -> e.setIsDeleted((Integer) v), Ha::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  test : test
   */
  public static final FieldMapping<Ha> test = new FieldMapping<Ha>
  	("test", "test", null, null, null, String.class, null)
  	.sg((e, v) -> e.setTest((String) v), Ha::getTest);

  /**
   * 实体属性 : 数据库字段 映射
   *  tex : tex
   */
  public static final FieldMapping<Ha> tex = new FieldMapping<Ha>
  	("tex", "tex", null, null, null, String.class, null)
  	.sg((e, v) -> e.setTex((String) v), Ha::getTex);

  /**
   * 实体属性 : 数据库字段 映射
   *  tst : tst
   */
  public static final FieldMapping<Ha> tst = new FieldMapping<Ha>
  	("tst", "tst", null, null, null, String.class, null)
  	.sg((e, v) -> e.setTst((String) v), Ha::getTst);

  /**
   * 实体属性 : 数据库字段 映射
   *  tt : tt
   */
  public static final FieldMapping<Ha> tt = new FieldMapping<Ha>
  	("tt", "tt", null, null, null, String.class, null)
  	.sg((e, v) -> e.setTt((String) v), Ha::getTt);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<Ha> updateTime = new FieldMapping<Ha>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), Ha::getUpdateTime);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, isDeleted, test, tex, tst, tt, updateTime));

  public static final HaMapping MAPPING = new HaMapping();

  protected HaMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return Ha.class;
  }

  @Override
  public Class mapperClass() {
    return HaMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new Ha();
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
  protected final HaQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new HaQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final HaUpdate updater(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new HaUpdate(defaults, fragment(table), alias, shared);
  }
}
