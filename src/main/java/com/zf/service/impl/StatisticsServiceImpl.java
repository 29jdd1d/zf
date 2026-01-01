package com.zf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zf.entity.Activity;
import com.zf.entity.ActivityRegistration;
import com.zf.entity.Student;
import com.zf.mapper.ActivityMapper;
import com.zf.mapper.ActivityRegistrationMapper;
import com.zf.mapper.StudentMapper;
import com.zf.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据统计服务实现类
 * 
 * @author zf
 * @since 2026-01-01
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private ActivityMapper activityMapper;
    
    @Autowired
    private ActivityRegistrationMapper registrationMapper;

    @Override
    public Map<String, Object> getClassOverview(Long classId) {
        Map<String, Object> result = new HashMap<>();
        
        // 学生总数
        LambdaQueryWrapper<Student> studentWrapper = new LambdaQueryWrapper<>();
        studentWrapper.eq(Student::getClassId, classId);
        long studentCount = studentMapper.selectCount(studentWrapper);
        result.put("studentCount", studentCount);
        
        // 活动总数
        LambdaQueryWrapper<Activity> activityWrapper = new LambdaQueryWrapper<>();
        activityWrapper.eq(Activity::getOrganizerId, classId)
                      .eq(Activity::getOrganizerType, "CLASS");
        long activityCount = activityMapper.selectCount(activityWrapper);
        result.put("activityCount", activityCount);
        
        return result;
    }

    @Override
    public Map<String, Object> getActivityParticipation(Long classId) {
        Map<String, Object> result = new HashMap<>();
        
        // 获取班级活动列表
        LambdaQueryWrapper<Activity> activityWrapper = new LambdaQueryWrapper<>();
        activityWrapper.eq(Activity::getOrganizerId, classId)
                      .eq(Activity::getOrganizerType, "CLASS");
        List<Activity> activities = activityMapper.selectList(activityWrapper);
        
        result.put("activities", activities);
        return result;
    }

    @Override
    public Map<String, Object> getPointsRanking(Long classId, Integer limit) {
        Map<String, Object> result = new HashMap<>();
        
        // 获取班级学生积分排名
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getClassId, classId)
               .orderByDesc(Student::getTotalPoints)
               .last("LIMIT " + (limit != null ? limit : 10));
        List<Student> students = studentMapper.selectList(wrapper);
        
        result.put("ranking", students);
        return result;
    }
}
