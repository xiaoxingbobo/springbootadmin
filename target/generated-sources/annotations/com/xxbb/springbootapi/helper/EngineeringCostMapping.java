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
import com.xxbb.springbootapi.entity.EngineeringCost;
import com.xxbb.springbootapi.mapper.EngineeringCostMapper;
import com.xxbb.springbootapi.wrapper.EngineeringCostQuery;
import com.xxbb.springbootapi.wrapper.EngineeringCostUpdate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * EngineeringCostMapping: Entity帮助类
 *
 * @author powered by FluentMybatis
 */
public class EngineeringCostMapping extends AMapping<EngineeringCost, EngineeringCostQuery, EngineeringCostUpdate> {
  /**
   * 表名称
   */
  public static final String TABLE_NAME = "engineering_cost";

  /**
   * Entity名称
   */
  public static final String ENTITY_NAME = "EngineeringCost";

  /**
   * 实体属性 : 数据库字段 映射
   *  id : id
   */
  public static final FieldMapping<EngineeringCost> id = new FieldMapping<EngineeringCost>
  	("id", "id", PRIMARY_ID, null, null, Integer.class, null)
  	.sg((e, v) -> e.setId((Integer) v), EngineeringCost::getId);

  /**
   * 实体属性 : 数据库字段 映射
   *  constructionCost : construction_cost
   */
  public static final FieldMapping<EngineeringCost> constructionCost = new FieldMapping<EngineeringCost>
  	("constructionCost", "construction_cost", null, null, null, Double.class, null)
  	.sg((e, v) -> e.setConstructionCost((Double) v), EngineeringCost::getConstructionCost);

  /**
   * 实体属性 : 数据库字段 映射
   *  createTime : create_time
   */
  public static final FieldMapping<EngineeringCost> createTime = new FieldMapping<EngineeringCost>
  	("createTime", "create_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setCreateTime((Date) v), EngineeringCost::getCreateTime);

  /**
   * 实体属性 : 数据库字段 映射
   *  independentFees : independent_fees
   */
  public static final FieldMapping<EngineeringCost> independentFees = new FieldMapping<EngineeringCost>
  	("independentFees", "independent_fees", null, null, null, Double.class, null)
  	.sg((e, v) -> e.setIndependentFees((Double) v), EngineeringCost::getIndependentFees);

  /**
   * 实体属性 : 数据库字段 映射
   *  isDeleted : is_deleted
   */
  public static final FieldMapping<EngineeringCost> isDeleted = new FieldMapping<EngineeringCost>
  	("isDeleted", "is_deleted", null, "0", null, Integer.class, null)
  	.sg((e, v) -> e.setIsDeleted((Integer) v), EngineeringCost::getIsDeleted);

  /**
   * 实体属性 : 数据库字段 映射
   *  name : name
   */
  public static final FieldMapping<EngineeringCost> name = new FieldMapping<EngineeringCost>
  	("name", "name", null, null, null, String.class, null)
  	.sg((e, v) -> e.setName((String) v), EngineeringCost::getName);

  /**
   * 实体属性 : 数据库字段 映射
   *  purchaseExpense : purchase_expense
   */
  public static final FieldMapping<EngineeringCost> purchaseExpense = new FieldMapping<EngineeringCost>
  	("purchaseExpense", "purchase_expense", null, null, null, Double.class, null)
  	.sg((e, v) -> e.setPurchaseExpense((Double) v), EngineeringCost::getPurchaseExpense);

  /**
   * 实体属性 : 数据库字段 映射
   *  total : total
   */
  public static final FieldMapping<EngineeringCost> total = new FieldMapping<EngineeringCost>
  	("total", "total", null, null, null, Double.class, null)
  	.sg((e, v) -> e.setTotal((Double) v), EngineeringCost::getTotal);

  /**
   * 实体属性 : 数据库字段 映射
   *  updateTime : update_time
   */
  public static final FieldMapping<EngineeringCost> updateTime = new FieldMapping<EngineeringCost>
  	("updateTime", "update_time", null, "now()", "now()", Date.class, null)
  	.sg((e, v) -> e.setUpdateTime((Date) v), EngineeringCost::getUpdateTime);

  public static final IDefaultSetter DEFAULT_SETTER = new IDefaultSetter(){};

  public static final List<FieldMapping> ALL_FIELD_MAPPING = Collections.unmodifiableList(Arrays
  	.asList(id, constructionCost, createTime, independentFees, isDeleted, name, purchaseExpense, total, updateTime));

  public static final EngineeringCostMapping MAPPING = new EngineeringCostMapping();

  protected EngineeringCostMapping() {
    super(DbType.MYSQL);
    super.tableName = TABLE_NAME;
    super.tableId = new TableId("id", "id", true, "", false);
    super.uniqueFields.put(PRIMARY_ID, id);
    super.Ref_Keys.unmodified();
  }

  @Override
  public Class entityClass() {
    return EngineeringCost.class;
  }

  @Override
  public Class mapperClass() {
    return EngineeringCostMapper.class;
  }

  @Override
  public <E extends IEntity> E newEntity() {
    return (E) new EngineeringCost();
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
  protected final EngineeringCostQuery query(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new EngineeringCostQuery(defaults, fragment(table), alias, shared);
  }

  @Override
  protected final EngineeringCostUpdate updater(boolean defaults, StringSupplier table,
      StringSupplier alias, Parameters shared) {
    return new EngineeringCostUpdate(defaults, fragment(table), alias, shared);
  }
}
