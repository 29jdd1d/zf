package com.zf.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动DTO
 * 
 * @author zf
 * @since 2026-01-01
 */
@Data
@ApiModel("活动请求对象")
public class ActivityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("活动ID")
    private Long id;

    @ApiModelProperty(value = "活动标题", required = true)
    @NotBlank(message = "活动标题不能为空")
    private String title;

    @ApiModelProperty(value = "活动类型ID", required = true)
    @NotNull(message = "活动类型不能为空")
    private Long typeId;

    @ApiModelProperty("组织者ID")
    private Long organizerId;

    @ApiModelProperty("组织者类型: CLASS, LEAGUE_BRANCH, DEPARTMENT")
    private String organizerType;

    @ApiModelProperty("活动描述")
    private String description;

    @ApiModelProperty("活动地点")
    private String location;

    @ApiModelProperty("场地ID")
    private Long venueId;

    @ApiModelProperty(value = "开始时间", required = true)
    @NotNull(message = "开始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    @NotNull(message = "结束时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty("报名截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDeadline;

    @ApiModelProperty("最大参与人数")
    private Integer maxParticipants;

    @ApiModelProperty("活动积分")
    private Integer points;

    @ApiModelProperty("封面图片")
    private String coverImage;
}
