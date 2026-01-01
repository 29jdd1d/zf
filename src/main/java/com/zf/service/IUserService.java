package com.zf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.dto.LoginDTO;
import com.zf.dto.UserDTO;
import com.zf.entity.User;
import com.zf.vo.LoginVO;

/**
 * 用户服务接口
 * 
 * @author zf
 * @since 2026-01-01
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 注册用户
     * @param userDTO 用户信息
     * @return 用户ID
     */
    Long register(UserDTO userDTO);

    /**
     * 修改密码
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(Long userId, String oldPassword, String newPassword);
}
