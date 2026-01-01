package com.zf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.entity.Activity;

/**
 * 活动服务接口
 * 
 * @author zf
 * @since 2026-01-01
 */
public interface IActivityService extends IService<Activity> {
    
    /**
     * 活动报名
     * @param activityId 活动ID
     * @param studentId 学生ID
     */
    void register(Long activityId, Long studentId);
    
    /**
     * 取消报名
     * @param activityId 活动ID
     * @param studentId 学生ID
     */
    void cancelRegistration(Long activityId, Long studentId);
    
    /**
     * 活动签到
     * @param activityId 活动ID
     * @param studentId 学生ID
     */
    void checkIn(Long activityId, Long studentId);
}
