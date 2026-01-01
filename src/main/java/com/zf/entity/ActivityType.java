package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动类型实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("activity_type")
public class ActivityType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动类型ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型代码
     */
    private String code;

    /**
     * 类型描述
     */
    private String description;

    /**
     * 图标
     */
    private String icon;

    /**
     * 默认积分
     */
    private Integer points;

    /**
     * 排序
     */
    private Integer sortOrder;

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
