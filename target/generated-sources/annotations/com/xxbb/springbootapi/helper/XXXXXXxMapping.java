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
import com.xxbb.springbootapi.mapper.XXXXXXxMapper;
import com.xxbb.springbootapi.wrapper.XXXXXXxQuery;
import com.xxbb.springbootapi.wrapper.XXXXXXxUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * XXXXXXxMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class XXXXXXxMapping extends AMapping<XXXXXXx, XXXXXXxQuery, XXXXXXxUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "x_x_x_x_x_xx";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "XXXXXXx";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<XXXXXXx> id = new FieldMapping<XXXXXXx>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), XXXXXXx::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<XXXXXXx> createTime = new FieldMapping<XXXXXXx>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), XXXXXXx::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<XXXXXXx> isDeleted = new FieldMapping<XXXXXXx>
  	("isDeleted", "is_deleted", LOGIC_DELETED, "0", null, Integer.class, null)
  	.sg((e, v) -> e.setIsDeleted((Integer) v), XXXXXXx::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<XXXXXXx> updateTime = new FieldMapping<XXXXXXx>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), XXXXXXx::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  xxxname : xxxname
   */
  public static final FieldMapping<XXXXXXx> xxxname = new FieldMapping<XXXXXXx>
  	("xxxname", "xxxname", null, null, null, Boolean.class, null)
  	.sg((e, v) -> e.setXxxname((Boolean) v), XXXXXXx::getXxxname);

  public static final <error> DEFAULT_SETTER = new <error>(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, createTime, isDeleted, updateTime, xxxname));

  public static final XXXXXXxMapping MAPPING = new XXXXXXxMapping();

  protected XXXXXXxMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.uniqueFields.put(LOGIC_DELETED, isDeleted);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return XXXXXXx.class;
  }

  @Override
  public Class mapperClass() {
    return XXXXXXxMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new XXXXXXx();
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
  protected final XXXXXXxQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new XXXXXXxQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final XXXXXXxUpdate updater(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new XXXXXXxUpdate(defaults, fragment(table), alias, shared);
  }
}
