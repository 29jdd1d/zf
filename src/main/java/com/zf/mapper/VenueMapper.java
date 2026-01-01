package com.zf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.entity.Venue;
import org.apache.ibatis.annotations.Mapper;

/**
 * 场地Mapper接口
 * 
 * @author zf
 * @since 2026-01-01
 */
@Mapper
public interface VenueMapper extends BaseMapper<Venue> {
}
