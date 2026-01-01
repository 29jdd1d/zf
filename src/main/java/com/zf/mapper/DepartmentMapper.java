package com.zf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * 院系Mapper接口
 * 
 * @author zf
 * @since 2026-01-01
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
}
