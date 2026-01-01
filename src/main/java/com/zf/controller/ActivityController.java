package com.zf.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.common.Result;
import com.zf.dto.ActivityDTO;
import com.zf.entity.Activity;
import com.zf.service.IActivityService;
import com.zf.utils.UserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 活动管理控制器
 * 
 * @author zf
 * @since 2026-01-01
 */
@Api(tags = "活动管理")
@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @ApiOperation("分页查询活动列表")
    @GetMapping("/page")
    public Result<Page<Activity>> page(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam("标题") @RequestParam(required = false) String title,
            @ApiParam("活动类型ID") @RequestParam(required = false) Long typeId,
            @ApiParam("状态") @RequestParam(required = false) String status) {
        
        Page<Activity> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Activity> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.hasText(title)) {
            wrapper.like(Activity::getTitle, title);
        }
        if (typeId != null) {
            wrapper.eq(Activity::getTypeId, typeId);
        }
        if (StringUtils.hasText(status)) {
            wrapper.eq(Activity::getStatus, status);
        }
        
        wrapper.orderByDesc(Activity::getStartTime);
        Page<Activity> result = activityService.page(page, wrapper);
        return Result.success(result);
    }

    @ApiOperation("创建活动")
    @PostMapping
    public Result<Void> create(@Validated @RequestBody ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);
        activity.setStatus("DRAFT");
        activity.setCurrentParticipants(0);
        activityService.save(activity);
        return Result.success("创建成功", null);
    }

    @ApiOperation("获取活动详情")
    @GetMapping("/{id}")
    public Result<Activity> getById(@PathVariable Long id) {
        Activity activity = activityService.getById(id);
        return Result.success(activity);
    }

    @ApiOperation("更新活动")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);
        activity.setId(id);
        activityService.updateById(activity);
        return Result.success("更新成功", null);
    }

    @ApiOperation("删除活动")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        activityService.removeById(id);
        return Result.success("删除成功", null);
    }

    @ApiOperation("活动报名")
    @PostMapping("/{id}/register")
    public Result<Void> register(@PathVariable Long id) {
        Long studentId = UserHolder.getUserId();
        activityService.register(id, studentId);
        return Result.success("报名成功", null);
    }

    @ApiOperation("取消报名")
    @PostMapping("/{id}/cancel")
    public Result<Void> cancelRegistration(@PathVariable Long id) {
        Long studentId = UserHolder.getUserId();
        activityService.cancelRegistration(id, studentId);
        return Result.success("取消报名成功", null);
    }

    @ApiOperation("活动签到")
    @PostMapping("/{id}/checkin")
    public Result<Void> checkIn(@PathVariable Long id) {
        Long studentId = UserHolder.getUserId();
        activityService.checkIn(id, studentId);
        return Result.success("签到成功", null);
    }
}
