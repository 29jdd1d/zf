package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动模板实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("activity_template")
public class ActivityTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模板ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 活动类型ID
     */
    private Long typeId;

    /**
     * 活动描述模板
     */
    private String description;

    /**
     * 默认时长(分钟)
     */
    private Integer duration;

    /**
     * 默认最大参与人数
     */
    private Integer maxParticipants;

    /**
     * 默认积分
     */
    private Integer points;

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
