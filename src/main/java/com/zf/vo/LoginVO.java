package com.zf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录返回VO
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("登录返回对象")
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("Token")
    private String token;
}
