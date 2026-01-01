package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资料共享实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资料ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 分类: 学习资料, 活动材料, 文档模板等
     */
    private String category;

    /**
     * 文件ID
     */
    private Long fileId;

    /**
     * 上传人ID
     */
    private Long uploaderId;

    /**
     * 共享范围: ALL-全部 CLASS-班级 DEPARTMENT-院系
     */
    private String targetType;

    /**
     * 目标ID
     */
    private Long targetId;

    /**
     * 下载次数
     */
    private Integer downloadCount;

    /**
     * 状态: DRAFT-草稿 PUBLISHED-已发布 WITHDRAWN-已撤回
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
