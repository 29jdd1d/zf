package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评优评先申请实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("honor_application")
public class HonorApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 申请ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目ID
     */
    private Long awardId;

    /**
     * 申请人ID
     */
    private Long applicantId;

    /**
     * 申请类型: STUDENT-学生 CLASS-班级
     */
    private String applicantType;

    /**
     * 申请时间
     */
    private LocalDateTime applicationTime;

    /**
     * 申请材料说明
     */
    private String materials;

    /**
     * 自我评价
     */
    private String selfEvaluation;

    /**
     * 状态: PENDING-待审核 APPROVED-已通过 REJECTED-已拒绝
     */
    private String status;

    /**
     * 审核人ID
     */
    private Long reviewerId;

    /**
     * 审核时间
     */
    private LocalDateTime reviewTime;

    /**
     * 审核意见
     */
    private String reviewComment;

    /**
     * 最终结果: AWARDED-获奖 NOT_AWARDED-未获奖
     */
    private String finalResult;

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
