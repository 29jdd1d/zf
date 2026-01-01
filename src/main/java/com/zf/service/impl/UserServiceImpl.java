package com.zf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.common.ResultCode;
import com.zf.dto.LoginDTO;
import com.zf.dto.UserDTO;
import com.zf.entity.User;
import com.zf.exception.BusinessException;
import com.zf.mapper.UserMapper;
import com.zf.service.IUserService;
import com.zf.utils.JwtUtil;
import com.zf.utils.PasswordUtil;
import com.zf.vo.LoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现类
 * 
 * @author zf
 * @since 2026-01-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        // 查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, loginDTO.getUsername());
        User user = this.getOne(wrapper);

        if (user == null) {
            throw new BusinessException(ResultCode.LOGIN_ERROR);
        }

        // 验证密码
        if (!PasswordUtil.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(ResultCode.LOGIN_ERROR);
        }

        // 检查用户状态
        if (user.getStatus() == 0) {
            throw new BusinessException(ResultCode.FORBIDDEN.getCode(), "用户已被禁用");
        }

        // 生成Token
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());

        // 构造返回对象
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(user.getId());
        loginVO.setUsername(user.getUsername());
        loginVO.setRealName(user.getRealName());
        loginVO.setRole(user.getRole());
        loginVO.setToken(token);

        return loginVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long register(UserDTO userDTO) {
        // 检查用户名是否已存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userDTO.getUsername());
        if (this.count(wrapper) > 0) {
            throw new BusinessException(ResultCode.USER_EXIST);
        }

        // 创建用户
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        
        // 加密密码
        if (userDTO.getPassword() != null) {
            user.setPassword(PasswordUtil.encode(userDTO.getPassword()));
        } else {
            // 默认密码
            user.setPassword(PasswordUtil.encode("123456"));
        }
        
        user.setStatus(1);
        this.save(user);

        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = this.getById(userId);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        // 验证旧密码
        if (!PasswordUtil.matches(oldPassword, user.getPassword())) {
            throw new BusinessException(ResultCode.PARAM_ERROR.getCode(), "旧密码不正确");
        }

        // 更新密码
        user.setPassword(PasswordUtil.encode(newPassword));
        this.updateById(user);
    }
}
