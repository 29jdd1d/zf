package com.zf.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密工具类
 * 
 * @author zf
 * @since 2026-01-01
 */
public class PasswordUtil {

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    /**
     * 密码加密
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String encode(String password) {
        return PASSWORD_ENCODER.encode(password);
    }

    /**
     * 密码验证
     * @param rawPassword 原始密码
     * @param encodedPassword 加密后的密码
     * @return true-匹配, false-不匹配
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
    }
}
