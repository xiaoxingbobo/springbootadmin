package com.xxbb.springbootapi.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
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

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
@Data
@FluentMybatis
@Entity
public class Authority extends Common{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动递增
    @TableId
    @ApiModelProperty(value = "主键",example = "0")
    private Integer id = super.id;
    @ApiModelProperty("创建时间")
    @TableField(insert = "now()", update = "now()")
    private Date createTime = super.createTime;
    @TableField(insert = "now()", update = "now()")
    @ApiModelProperty("修改时间")
    private Date updateTime = super.updateTime;
    @TableField(insert = "0")
    @ApiModelProperty(value = "是否删除",example = "0")
    private Integer isDeleted = super.isDeleted;
    @ApiModelProperty(value = "权限")
    private String value;
    @ApiModelProperty(value = "名称")
    private String name;
}
