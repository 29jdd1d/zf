package com.zf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通知公告Mapper接口
 * 
 * @author zf
 * @since 2026-01-01
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
