package com.zf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.entity.ActivityAttendance;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动签到Mapper接口
 * 
 * @author zf
 * @since 2026-01-01
 */
@Mapper
public interface ActivityAttendanceMapper extends BaseMapper<ActivityAttendance> {
}
