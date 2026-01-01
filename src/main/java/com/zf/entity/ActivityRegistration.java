package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动报名实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("activity_registration")
public class ActivityRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报名ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 报名时间
     */
    private LocalDateTime registrationTime;

    /**
     * 状态: REGISTERED-已报名 CANCELLED-已取消 ATTENDED-已参加
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

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
