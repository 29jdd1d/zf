package com.zf.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回状态码枚举
 * 
 * @author zf
 * @since 2026-01-01
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 失败
     */
    ERROR(500, "操作失败"),

    /**
     * 参数错误
     */
    PARAM_ERROR(400, "参数错误"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权，请先登录"),

    /**
     * 无权限
     */
    FORBIDDEN(403, "无权限访问"),

    /**
     * 资源未找到
     */
    NOT_FOUND(404, "资源未找到"),

    /**
     * 用户名或密码错误
     */
    LOGIN_ERROR(1001, "用户名或密码错误"),

    /**
     * 用户已存在
     */
    USER_EXIST(1002, "用户已存在"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST(1003, "用户不存在"),

    /**
     * Token无效
     */
    TOKEN_INVALID(1004, "Token无效"),

    /**
     * Token过期
     */
    TOKEN_EXPIRED(1005, "Token已过期"),

    /**
     * 数据已存在
     */
    DATA_EXIST(2001, "数据已存在"),

    /**
     * 数据不存在
     */
    DATA_NOT_EXIST(2002, "数据不存在"),

    /**
     * 文件上传失败
     */
    FILE_UPLOAD_ERROR(3001, "文件上传失败"),

    /**
     * 文件下载失败
     */
    FILE_DOWNLOAD_ERROR(3002, "文件下载失败"),

    /**
     * 业务异常
     */
    BUSINESS_ERROR(4001, "业务异常");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 返回消息
     */
    private final String message;
}
