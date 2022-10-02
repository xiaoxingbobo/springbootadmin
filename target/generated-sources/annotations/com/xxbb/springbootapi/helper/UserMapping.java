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
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.mapper.UserMapper;
import com.xxbb.springbootapi.wrapper.UserQuery;
import com.xxbb.springbootapi.wrapper.UserUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * UserMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class UserMapping extends AMapping<User, UserQuery, UserUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "user";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "User";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<User> id = new FieldMapping<User>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), User::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  age : age
   */
  public static final FieldMapping<User> age = new FieldMapping<User>
  	("age", "age", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setAge((Integer) v), User::getAge);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<User> createTime = new FieldMapping<User>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), User::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<User> isDeleted = new FieldMapping<User>
  	("isDeleted", "is_deleted", null, "0", null, Integer.class, null)
  	.sg((e, v) -> e.setIsDeleted((Integer) v), User::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  name : name
   */
  public static final FieldMapping<User> name = new FieldMapping<User>
  	("name", "name", null, null, null, String.class, null)
  	.sg((e, v) -> e.setName((String) v), User::getName);

  /**
   * 实体属性 : 数据库字段 映射
   *  sex : sex
   */
  public static final FieldMapping<User> sex = new FieldMapping<User>
  	("sex", "sex", null, null, null, Short.class, null)
  	.sg((e, v) -> e.setSex((Short) v), User::getSex);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<User> updateTime = new FieldMapping<User>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), User::getUpdateTime);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, age, createTime, isDeleted, name, sex, updateTime));

  public static final UserMapping MAPPING = new UserMapping();

  protected UserMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return User.class;
  }

  @Override
  public Class mapperClass() {
    return UserMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new User();
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
  protected final UserQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new UserQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final UserUpdate updater(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new UserUpdate(defaults, fragment(table), alias, shared);
  }
}
