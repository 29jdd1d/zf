package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 积分规则实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("points_rule")
public class PointsRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 规则ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 规则类型: ACTIVITY, AWARD, VIOLATION
     */
    private String type;

    /**
     * 积分值
     */
    private Integer points;

    /**
     * 规则描述
     */
    private String description;

    /**
     * 状态: ACTIVE-启用 INACTIVE-停用
     */
    private String status;

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
