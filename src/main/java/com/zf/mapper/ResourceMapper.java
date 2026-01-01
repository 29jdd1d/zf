package com.zf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资料共享Mapper接口
 * 
 * @author zf
 * @since 2026-01-01
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
}
