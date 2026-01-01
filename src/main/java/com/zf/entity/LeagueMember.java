package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 团员信息实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("league_member")
public class LeagueMember implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 团员ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 团支部ID
     */
    private Long leagueBranchId;

    /**
     * 团员证号
     */
    private String membershipNo;

    /**
     * 入团日期
     */
    private LocalDate joinDate;

    /**
     * 状态: ACTIVE-在籍 TRANSFERRED-转出 QUIT-退团
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
