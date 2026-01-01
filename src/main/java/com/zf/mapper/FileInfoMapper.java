package com.zf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件信息Mapper接口
 * 
 * @author zf
 * @since 2026-01-01
 */
@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {
}
