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

import javax.persistence.*;
import java.util.Date;

/**
 * 代码生成记录
 *
 * @author
 */

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
@Data
@FluentMybatis(defaults = IFMConfig.class)//defaults设置默认查询条件
@Entity
public class GenRecord extends Common {
    //公共属性
    @Id
    @TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动递增
    @Column(nullable = false)
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
    @TableField(insert = "0")
    @LogicDelete
    @ApiModelProperty(value = "是否删除", example = "0")
    private Boolean isDeleted = super.isDeleted;
    //自定义属性
    @ApiModelProperty(value = "实体名称")
    private String entityName;
    @ApiModelProperty(value = "权限Id")
    private String authorityId;
}
