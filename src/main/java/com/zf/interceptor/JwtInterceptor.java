package com.zf.interceptor;

import com.zf.common.ResultCode;
import com.zf.entity.User;
import com.zf.exception.BusinessException;
import com.zf.mapper.UserMapper;
import com.zf.utils.JwtUtil;
import com.zf.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT认证拦截器
 * 
 * @author zf
 * @since 2026-01-01
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从请求头中获取Token
        String token = request.getHeader(header);
        
        if (!StringUtils.hasText(token)) {
            throw new BusinessException(ResultCode.UNAUTHORIZED);
        }

        // 去除Bearer前缀
        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length());
        }

        // 验证Token
        if (!jwtUtil.validateToken(token)) {
            throw new BusinessException(ResultCode.TOKEN_INVALID);
        }

        // 从Token中获取用户信息
        Long userId = jwtUtil.getUserIdFromToken(token);
        User user = userMapper.selectById(userId);
        
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        if (user.getStatus() == 0) {
            throw new BusinessException(ResultCode.FORBIDDEN.getCode(), "用户已被禁用");
        }

        // 将用户信息存入ThreadLocal
        UserHolder.setUser(user);
        
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清除ThreadLocal中的用户信息
        UserHolder.removeUser();
    }
}
