package com.xxbb.springbootapi.entity.dto;

import com.xxbb.springbootapi.entity.SysRoleAuthority;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor//有参数构造
@Accessors(chain = true)//链式调用
@NoArgsConstructor//无参数构造
@Data
public class SysRoleAuthorityResult extends SysRoleAuthority {
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "权限值")
    private String authorityValue;
    @ApiModelProperty(value = "权限名称")
    private String authorityName;
}
