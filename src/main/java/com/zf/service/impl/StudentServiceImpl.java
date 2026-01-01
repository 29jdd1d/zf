package com.zf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.entity.Student;
import com.zf.mapper.StudentMapper;
import com.zf.service.IStudentService;
import org.springframework.stereotype.Service;

/**
 * 学生服务实现类
 * 
 * @author zf
 * @since 2026-01-01
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
}
