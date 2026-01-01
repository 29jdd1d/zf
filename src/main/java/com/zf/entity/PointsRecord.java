package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 积分记录实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("points_record")
public class PointsRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 积分变动(正数为加分, 负数为减分)
     */
    private Integer points;

    /**
     * 类型: ACTIVITY-活动 AWARD-获奖 VIOLATION-违纪 MANUAL-手动调整
     */
    private String type;

    /**
     * 来源ID(如活动ID, 获奖ID)
     */
    private Long sourceId;

    /**
     * 来源类型
     */
    private String sourceType;

    /**
     * 积分说明
     */
    private String reason;

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 记录时间
     */
    private LocalDateTime recordTime;

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
