package com.zf.controller;

import com.zf.common.Result;
import com.zf.service.IStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 数据统计控制器
 * 
 * @author zf
 * @since 2026-01-01
 */
@Api(tags = "数据统计")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private IStatisticsService statisticsService;

    @ApiOperation("获取班级概览数据")
    @GetMapping("/class/{classId}/overview")
    public Result<Map<String, Object>> getClassOverview(@PathVariable Long classId) {
        Map<String, Object> data = statisticsService.getClassOverview(classId);
        return Result.success(data);
    }

    @ApiOperation("获取活动参与率统计")
    @GetMapping("/class/{classId}/activity-participation")
    public Result<Map<String, Object>> getActivityParticipation(@PathVariable Long classId) {
        Map<String, Object> data = statisticsService.getActivityParticipation(classId);
        return Result.success(data);
    }

    @ApiOperation("获取积分排行榜")
    @GetMapping("/class/{classId}/points-ranking")
    public Result<Map<String, Object>> getPointsRanking(
            @PathVariable Long classId,
            @ApiParam("排行数量") @RequestParam(defaultValue = "10") Integer limit) {
        Map<String, Object> data = statisticsService.getPointsRanking(classId, limit);
        return Result.success(data);
    }
}
