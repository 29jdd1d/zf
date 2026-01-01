package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知公告实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 通知ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容(支持富文本)
     */
    private String content;

    /**
     * 类型: ANNOUNCEMENT-公告 NOTICE-通知 NEWS-新闻
     */
    private String type;

    /**
     * 级别: URGENT-紧急 IMPORTANT-重要 NORMAL-普通
     */
    private String level;

    /**
     * 发布人ID
     */
    private Long publisherId;

    /**
     * 推送目标: ALL-全部 CLASS-班级 STUDENT-个人
     */
    private String targetType;

    /**
     * 目标ID(班级ID或学生ID)
     */
    private Long targetId;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 状态: DRAFT-草稿 PUBLISHED-已发布 WITHDRAWN-已撤回
     */
    private String status;

    /**
     * 阅读数
     */
    private Integer readCount;

    /**
     * 附件ID列表(JSON)
     */
    private String attachmentIds;

    /**
     * 置顶标识: 0-否 1-是
     */
    private Integer topFlag;

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
