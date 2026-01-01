package com.zf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.entity.Instructor;
import org.apache.ibatis.annotations.Mapper;

/**
 * 辅导员Mapper接口
 * 
 * @author zf
 * @since 2026-01-01
 */
@Mapper
public interface InstructorMapper extends BaseMapper<Instructor> {
}
