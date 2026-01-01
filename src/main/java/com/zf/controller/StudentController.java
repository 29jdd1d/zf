package com.zf.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.common.Result;
import com.zf.dto.StudentDTO;
import com.zf.entity.Student;
import com.zf.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 学生管理控制器
 * 
 * @author zf
 * @since 2026-01-01
 */
@Api(tags = "学生管理")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @ApiOperation("分页查询学生列表")
    @GetMapping("/page")
    public Result<Page<Student>> page(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam("姓名") @RequestParam(required = false) String name,
            @ApiParam("学号") @RequestParam(required = false) String studentNo,
            @ApiParam("班级ID") @RequestParam(required = false) Long classId) {
        
        Page<Student> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.hasText(name)) {
            wrapper.like(Student::getName, name);
        }
        if (StringUtils.hasText(studentNo)) {
            wrapper.like(Student::getStudentNo, studentNo);
        }
        if (classId != null) {
            wrapper.eq(Student::getClassId, classId);
        }
        
        wrapper.orderByDesc(Student::getCreatedTime);
        Page<Student> result = studentService.page(page, wrapper);
        return Result.success(result);
    }

    @ApiOperation("创建学生")
    @PostMapping
    public Result<Void> create(@Validated @RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        studentService.save(student);
        return Result.success("创建成功", null);
    }

    @ApiOperation("获取学生详情")
    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @ApiOperation("更新学生信息")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        student.setId(id);
        studentService.updateById(student);
        return Result.success("更新成功", null);
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        studentService.removeById(id);
        return Result.success("删除成功", null);
    }
}
