package com.xxbb.springbootapi;

import com.xxbb.springbootapi.helper.AuthorityMapping;
import com.xxbb.springbootapi.helper.CommonMapping;
import com.xxbb.springbootapi.helper.GenRecordMapping;
import com.xxbb.springbootapi.helper.RoleAuthorityMapping;
import com.xxbb.springbootapi.helper.RoleMapping;
import com.xxbb.springbootapi.helper.TestMapping;
import com.xxbb.springbootapi.helper.UserMapping;

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
    final class Authority extends AuthorityMapping {
    }

    final class Common extends CommonMapping {
    }

    final class GenRecord extends GenRecordMapping {
    }

    final class Role extends RoleMapping {
    }

    final class RoleAuthority extends RoleAuthorityMapping {
    }

    final class Test extends TestMapping {
    }

    final class User extends UserMapping {
    }
  }

  interface Query {
    AuthorityMapping authority = AuthorityMapping.MAPPING;

    CommonMapping common = CommonMapping.MAPPING;

    GenRecordMapping genRecord = GenRecordMapping.MAPPING;

    RoleMapping role = RoleMapping.MAPPING;

    RoleAuthorityMapping roleAuthority = RoleAuthorityMapping.MAPPING;

    TestMapping test = TestMapping.MAPPING;

    UserMapping user = UserMapping.MAPPING;
  }
}
