package com.zf.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 场地实体
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@TableName("venue")
public class Venue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 场地ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场地名称
     */
    private String name;

    /**
     * 场地位置
     */
    private String location;

    /**
     * 容纳人数
     */
    private Integer capacity;

    /**
     * 设施说明
     */
    private String facilities;

    /**
     * 负责人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 状态: AVAILABLE-可用 UNAVAILABLE-不可用 MAINTENANCE-维护中
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
