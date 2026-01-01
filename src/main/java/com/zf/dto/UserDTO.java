package com.zf.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户DTO
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@ApiModel("用户请求对象")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty("头像URL")
    private String avatar;

    @ApiModelProperty(value = "角色: ADMIN, INSTRUCTOR, STUDENT", required = true)
    @NotBlank(message = "角色不能为空")
    private String role;

    @ApiModelProperty("状态: 0-禁用 1-启用")
    private Integer status;
}
