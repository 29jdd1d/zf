package com.zf.utils;

import com.zf.entity.User;

/**
 * 用户信息持有者(ThreadLocal)
 * 
 * @author zf
 * @since 2026-01-01
 */
public class UserHolder {

    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前用户
     * @param user 用户对象
     */
    public static void setUser(User user) {
        USER_THREAD_LOCAL.set(user);
    }

    /**
     * 获取当前用户
     * @return 用户对象
     */
    public static User getUser() {
        return USER_THREAD_LOCAL.get();
    }

    /**
     * 获取当前用户ID
     * @return 用户ID
     */
    public static Long getUserId() {
        User user = USER_THREAD_LOCAL.get();
        return user != null ? user.getId() : null;
    }

    /**
     * 获取当前用户名
     * @return 用户名
     */
    public static String getUsername() {
        User user = USER_THREAD_LOCAL.get();
        return user != null ? user.getUsername() : null;
    }

    /**
     * 获取当前用户角色
     * @return 角色
     */
    public static String getRole() {
        User user = USER_THREAD_LOCAL.get();
        return user != null ? user.getRole() : null;
    }

    /**
     * 清除当前用户
     */
    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }
}
