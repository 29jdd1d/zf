package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 学生实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private LocalDate birthDate;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 院系ID
     */
    private Long departmentId;

    /**
     * 入学日期
     */
    private LocalDate enrollmentDate;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 家庭住址
     */
    private String homeAddress;

    /**
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 紧急联系电话
     */
    private String emergencyPhone;

    /**
     * 总积分
     */
    private Integer totalPoints;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;
}
