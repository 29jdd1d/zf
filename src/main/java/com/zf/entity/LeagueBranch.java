package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 团支部实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("league_branch")
public class LeagueBranch implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 团支部ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 团支部名称
     */
    private String name;

    /**
     * 所属班级ID
     */
    private Long classId;

    /**
     * 团支书ID
     */
    private Long secretaryId;

    /**
     * 团员人数
     */
    private Integer memberCount;

    /**
     * 成立日期
     */
    private LocalDate establishmentDate;

    /**
     * 团支部描述
     */
    private String description;

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
