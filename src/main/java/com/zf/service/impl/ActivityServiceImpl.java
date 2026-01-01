package com.zf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.common.ResultCode;
import com.zf.entity.Activity;
import com.zf.entity.ActivityAttendance;
import com.zf.entity.ActivityRegistration;
import com.zf.exception.BusinessException;
import com.zf.mapper.ActivityAttendanceMapper;
import com.zf.mapper.ActivityMapper;
import com.zf.mapper.ActivityRegistrationMapper;
import com.zf.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 活动服务实现类
 * 
 * @author zf
 * @since 2026-01-01
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private ActivityRegistrationMapper registrationMapper;
    
    @Autowired
    private ActivityAttendanceMapper attendanceMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(Long activityId, Long studentId) {
        // 检查活动是否存在
        Activity activity = this.getById(activityId);
        if (activity == null) {
            throw new BusinessException(ResultCode.DATA_NOT_EXIST.getCode(), "活动不存在");
        }
        
        // 检查是否已报名
        LambdaQueryWrapper<ActivityRegistration> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ActivityRegistration::getActivityId, activityId)
               .eq(ActivityRegistration::getStudentId, studentId);
        if (registrationMapper.selectCount(wrapper) > 0) {
            throw new BusinessException(ResultCode.DATA_EXIST.getCode(), "已报名该活动");
        }
        
        // 检查报名人数是否已满
        if (activity.getMaxParticipants() != null && 
            activity.getCurrentParticipants() >= activity.getMaxParticipants()) {
            throw new BusinessException(ResultCode.BUSINESS_ERROR.getCode(), "报名人数已满");
        }
        
        // 创建报名记录
        ActivityRegistration registration = new ActivityRegistration();
        registration.setActivityId(activityId);
        registration.setStudentId(studentId);
        registration.setRegistrationTime(LocalDateTime.now());
        registration.setStatus("REGISTERED");
        registrationMapper.insert(registration);
        
        // 更新活动参与人数
        activity.setCurrentParticipants(activity.getCurrentParticipants() + 1);
        this.updateById(activity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelRegistration(Long activityId, Long studentId) {
        // 查询报名记录
        LambdaQueryWrapper<ActivityRegistration> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ActivityRegistration::getActivityId, activityId)
               .eq(ActivityRegistration::getStudentId, studentId);
        ActivityRegistration registration = registrationMapper.selectOne(wrapper);
        
        if (registration == null) {
            throw new BusinessException(ResultCode.DATA_NOT_EXIST.getCode(), "未报名该活动");
        }
        
        // 更新报名状态
        registration.setStatus("CANCELLED");
        registrationMapper.updateById(registration);
        
        // 更新活动参与人数
        Activity activity = this.getById(activityId);
        if (activity.getCurrentParticipants() > 0) {
            activity.setCurrentParticipants(activity.getCurrentParticipants() - 1);
            this.updateById(activity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkIn(Long activityId, Long studentId) {
        // 检查是否已报名
        LambdaQueryWrapper<ActivityRegistration> regWrapper = new LambdaQueryWrapper<>();
        regWrapper.eq(ActivityRegistration::getActivityId, activityId)
                  .eq(ActivityRegistration::getStudentId, studentId)
                  .eq(ActivityRegistration::getStatus, "REGISTERED");
        if (registrationMapper.selectCount(regWrapper) == 0) {
            throw new BusinessException(ResultCode.BUSINESS_ERROR.getCode(), "未报名该活动");
        }
        
        // 检查是否已签到
        LambdaQueryWrapper<ActivityAttendance> attWrapper = new LambdaQueryWrapper<>();
        attWrapper.eq(ActivityAttendance::getActivityId, activityId)
                  .eq(ActivityAttendance::getStudentId, studentId);
        ActivityAttendance attendance = attendanceMapper.selectOne(attWrapper);
        
        if (attendance != null) {
            throw new BusinessException(ResultCode.DATA_EXIST.getCode(), "已签到该活动");
        }
        
        // 创建签到记录
        attendance = new ActivityAttendance();
        attendance.setActivityId(activityId);
        attendance.setStudentId(studentId);
        attendance.setCheckInTime(LocalDateTime.now());
        attendance.setStatus("PRESENT");
        attendanceMapper.insert(attendance);
    }
}
