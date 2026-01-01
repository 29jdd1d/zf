package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 辅导员实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("instructor")
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 辅导员ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 工号
     */
    private String employeeNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所属院系ID
     */
    private Long departmentId;

    /**
     * 办公地点
     */
    private String officeLocation;

    /**
     * 职称
     */
    private String title;

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
