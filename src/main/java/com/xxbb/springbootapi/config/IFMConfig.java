package com.xxbb.springbootapi.config;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.crud.IUpdate;
import com.xxbb.springbootapi.entity.Common;

import java.util.Date;

import static cn.org.atool.fluent.mybatis.base.model.SqlOp.EQ;

public interface IFMConfig extends IDefaultSetter {
    String F_ENV = "env";

    String F_IS_DELETED = "is_deleted";

    String TEST_ENV = "test_env";

    /**
     * 设置默认查询方式
     *
     * @param query
     */
    @Override
    default void setQueryDefault(IQuery query) {
        query.where().apply(F_IS_DELETED, EQ, false)//默认查询未删除的数据
                //.apply(F_ENV, EQ, TEST_ENV)//默认查询测试环境的数据
                .end();
    }

    @Override
    default void setUpdateDefault(IUpdate updater) {
        updater.updateSet("update_time" , new Date());//修改时间
    }

    /**
     * 设置默认插入数据
     *
     * @param entity
     */
    @Override
    default void setInsertDefault(IEntity entity) {
        if (!(entity instanceof Common)) {
            return;
        }
        Common common = (Common) entity;
        common.setCreateTime(new Date());
        common.setIsDeleted(false);
        common.setUpdateTime(new Date());
        common.setId(null);
    }
}