package com.xxbb.springbootapi.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.LogicDelete;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxbb.springbootapi.config.IFMConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The type Authority.
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
@Data
@FluentMybatis(defaults = IFMConfig.class)//defaults设置默认查询条件
@Entity
public class Authority extends Common {
    //基础属性
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动递增
    @TableId
    @ApiModelProperty(value = "主键", example = "0")
    private Integer id = super.id;
    @ApiModelProperty("创建时间")
    @TableField(insert = "now()", update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = super.createTime;
    @TableField(insert = "now()", update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("修改时间")
    private Date updateTime = super.updateTime;

    //特有属性
    @ApiModelProperty(value = "是否删除", example = "0")
    @LogicDelete
    @TableField(value = "is_deleted", insert = "0")
    private Boolean isDeleted = super.isDeleted;
    @ApiModelProperty(value = "权限")
    private String value;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "上级权限Id")
    private Integer parentId;
}
