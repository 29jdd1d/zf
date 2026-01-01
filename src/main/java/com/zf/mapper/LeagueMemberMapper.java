package com.zf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.entity.LeagueMember;
import org.apache.ibatis.annotations.Mapper;

/**
 * 团员信息Mapper接口
 * 
 * @author zf
 * @since 2026-01-01
 */
@Mapper
public interface LeagueMemberMapper extends BaseMapper<LeagueMember> {
}
