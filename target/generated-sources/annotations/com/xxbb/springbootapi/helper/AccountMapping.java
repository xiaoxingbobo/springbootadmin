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
import com.xxbb.springbootapi.entity.Account;
import com.xxbb.springbootapi.mapper.AccountMapper;
import com.xxbb.springbootapi.wrapper.AccountQuery;
import com.xxbb.springbootapi.wrapper.AccountUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * AccountMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class AccountMapping extends AMapping<Account, AccountQuery, AccountUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "account";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "Account";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<Account> id = new FieldMapping<Account>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), Account::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  account : account
   */
  public static final FieldMapping<Account> account = new FieldMapping<Account>
  	("account", "account", null, null, null, String.class, null)
  	.sg((e, v) -> e.setAccount((String) v), Account::getAccount);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<Account> createTime = new FieldMapping<Account>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), Account::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  email : email
   */
  public static final FieldMapping<Account> email = new FieldMapping<Account>
  	("email", "email", null, null, null, String.class, null)
  	.sg((e, v) -> e.setEmail((String) v), Account::getEmail);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<Account> isDeleted = new FieldMapping<Account>
  	("isDeleted", "is_deleted", null, "0", null, Integer.class, null)
  	.sg((e, v) -> e.setIsDeleted((Integer) v), Account::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  password : password
   */
  public static final FieldMapping<Account> password = new FieldMapping<Account>
  	("password", "password", null, null, null, String.class, null)
  	.sg((e, v) -> e.setPassword((String) v), Account::getPassword);

  /**
   * 实体属性 : 数据库字段 映射
   *  roleId : role_id
   */
  public static final FieldMapping<Account> roleId = new FieldMapping<Account>
  	("roleId", "role_id", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setRoleId((Integer) v), Account::getRoleId);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<Account> updateTime = new FieldMapping<Account>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), Account::getUpdateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  userId : user_id
   */
  public static final FieldMapping<Account> userId = new FieldMapping<Account>
  	("userId", "user_id", null, null, null, Integer.class, null)
  	.sg((e, v) -> e.setUserId((Integer) v), Account::getUserId);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, account, createTime, email, isDeleted, password, roleId, updateTime, userId));

  public static final AccountMapping MAPPING = new AccountMapping();

  protected AccountMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return Account.class;
  }

  @Override
  public Class mapperClass() {
    return AccountMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new Account();
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
  protected final AccountQuery query(boolean defaults, StringSupplier table, StringSupplier alias,
      Parameters shared) {
    return new AccountQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final AccountUpdate updater(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new AccountUpdate(defaults, fragment(table), alias, shared);
  }
}
