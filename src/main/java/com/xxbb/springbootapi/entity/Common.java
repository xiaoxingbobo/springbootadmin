package com.xxbb.springbootapi.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.LogicDelete;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import com.xxbb.springbootapi.config.IFMConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
@FluentMybatis(defaults = IFMConfig.class)//defaults设置默认查询条件
public class Common extends RichEntity implements Serializable {
    @TableId
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "主键", example = "0")
    protected Integer id;
    @TableField(insert = "now()", update = "now()")
    protected Date createTime;
    @TableField(insert = "now()", update = "now()")
    protected Date updateTime;
    @TableField(insert = "0")
    @LogicDelete
    @ApiModelProperty(value = "是否删除", example = "0")
    protected Boolean isDeleted = Boolean.FALSE;
}
