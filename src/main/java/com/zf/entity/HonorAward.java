package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评优评先项目实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("honor_award")
public class HonorAward implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 类型: 个人奖项, 集体奖项
     */
    private String type;

    /**
     * 级别: 校级, 院级, 班级
     */
    private String level;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 名额
     */
    private Integer quota;

    /**
     * 申请开始时间
     */
    private LocalDateTime applicationStartTime;

    /**
     * 申请截止时间
     */
    private LocalDateTime applicationEndTime;

    /**
     * 评选标准
     */
    private String evaluationCriteria;

    /**
     * 状态: OPEN-开放 CLOSED-关闭 COMPLETED-已完成
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
