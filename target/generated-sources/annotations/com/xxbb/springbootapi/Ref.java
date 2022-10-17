package com.xxbb.springbootapi;

import com.xxbb.springbootapi.helper.PeopleMapping;

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
    final class People extends PeopleMapping {
    }
  }

  interface Query {
    PeopleMapping people = PeopleMapping.MAPPING;
  }
}
