package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 班委会成员实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("class_committee")
public class ClassCommittee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班委ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 职位: 班长, 团支书, 学习委员等
     */
    private String position;

    /**
     * 任期开始日期
     */
    private LocalDate termStart;

    /**
     * 任期结束日期
     */
    private LocalDate termEnd;

    /**
     * 状态: ACTIVE-在任 EXPIRED-已卸任
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
