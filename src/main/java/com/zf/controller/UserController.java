package com.zf.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.common.Result;
import com.zf.dto.UserDTO;
import com.zf.entity.User;
import com.zf.service.IUserService;
import com.zf.utils.UserHolder;
import com.zf.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 * 
 * @author zf
 * @since 2026-01-01
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("获取当前用户信息")
    @GetMapping("/current")
    public Result<UserVO> getCurrentUser() {
        User user = UserHolder.getUser();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<Long> register(@Validated @RequestBody UserDTO userDTO) {
        Long userId = userService.register(userDTO);
        return Result.success("注册成功", userId);
    }

    @ApiOperation("修改密码")
    @PutMapping("/password")
    public Result<Void> changePassword(
            @ApiParam("旧密码") @RequestParam String oldPassword,
            @ApiParam("新密码") @RequestParam String newPassword) {
        Long userId = UserHolder.getUserId();
        userService.changePassword(userId, oldPassword, newPassword);
        return Result.success("密码修改成功", null);
    }

    @ApiOperation("分页查询用户列表")
    @GetMapping("/page")
    public Result<Page<User>> page(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam("用户名") @RequestParam(required = false) String username,
            @ApiParam("角色") @RequestParam(required = false) String role) {
        
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.hasText(username)) {
            wrapper.like(User::getUsername, username);
        }
        if (StringUtils.hasText(role)) {
            wrapper.eq(User::getRole, role);
        }
        
        wrapper.orderByDesc(User::getCreatedTime);
        Page<User> result = userService.page(page, wrapper);
        return Result.success(result);
    }

    @ApiOperation("获取用户详情")
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setId(id);
        userService.updateById(user);
        return Result.success("更新成功", null);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success("删除成功", null);
    }
}
