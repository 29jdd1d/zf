package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动类型ID
     */
    private Long typeId;

    /**
     * 组织者ID
     */
    private Long organizerId;

    /**
     * 组织者类型: CLASS, LEAGUE_BRANCH, DEPARTMENT
     */
    private String organizerType;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 场地ID
     */
    private Long venueId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 报名截止时间
     */
    private LocalDateTime registrationDeadline;

    /**
     * 最大参与人数
     */
    private Integer maxParticipants;

    /**
     * 当前参与人数
     */
    private Integer currentParticipants;

    /**
     * 活动积分
     */
    private Integer points;

    /**
     * 状态: DRAFT-草稿 PENDING-待审核 APPROVED-已通过 REJECTED-已拒绝 ONGOING-进行中 COMPLETED-已完成 CANCELLED-已取消
     */
    private String status;

    /**
     * 审批状态
     */
    private String approvalStatus;

    /**
     * 审批人ID
     */
    private Long approverId;

    /**
     * 审批时间
     */
    private LocalDateTime approvalTime;

    /**
     * 审批意见
     */
    private String approvalComment;

    /**
     * 封面图片
     */
    private String coverImage;

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
