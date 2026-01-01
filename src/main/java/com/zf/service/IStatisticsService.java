package com.zf.service;

import java.util.Map;

/**
 * 数据统计服务接口
 * 
 * @author zf
 * @since 2026-01-01
 */
public interface IStatisticsService {
    
    /**
     * 获取班级概览数据
     * @param classId 班级ID
     * @return 统计数据
     */
    Map<String, Object> getClassOverview(Long classId);
    
    /**
     * 获取活动参与率统计
     * @param classId 班级ID
     * @return 统计数据
     */
    Map<String, Object> getActivityParticipation(Long classId);
    
    /**
     * 获取积分排行榜
     * @param classId 班级ID
     * @param limit 排行数量
     * @return 排行数据
     */
    Map<String, Object> getPointsRanking(Long classId, Integer limit);
}
