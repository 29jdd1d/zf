package com.zf.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 学生DTO
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@ApiModel("学生请求对象")
public class StudentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学生ID")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty(value = "学号", required = true)
    @NotBlank(message = "学号不能为空")
    private String studentNo;

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("班级ID")
    private Long classId;

    @ApiModelProperty("院系ID")
    private Long departmentId;

    @ApiModelProperty("入学日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmentDate;

    @ApiModelProperty("政治面貌")
    private String politicalStatus;

    @ApiModelProperty("家庭住址")
    private String homeAddress;

    @ApiModelProperty("紧急联系人")
    private String emergencyContact;

    @ApiModelProperty("紧急联系电话")
    private String emergencyPhone;
}
