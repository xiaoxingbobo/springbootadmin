package com.xxbb.springbootapi;

import com.xxbb.springbootapi.helper.GenRecordMapping;

/**
 *
 * Ref: 
 *  o - 查询器，更新器工厂类单例引用
 *  o - 应用所有Mapper Bean引用
 *  o - Entity关联对象延迟加载查询实现
 *
 * @author powered by FluentMybatis
 */
public interface Ref {
  /**
   * 所有Entity FieldMapping引用
   */
  interface Field {
    final class GenRecord extends GenRecordMapping {
    }
  }

  interface Query {
    GenRecordMapping genRecord = GenRecordMapping.MAPPING;
  }
}
