package com.theo.usercenterbackend.constant;

/**
 * 用户常量
 * 在用户接口中需要使用到如 用户登录状态、用户角色等 所以单独写一个常量类来管理这些常量
 */
public interface userConstant {
    /**
     *
     * 用户登录 态键
     */
    String USER_LOGIN_STATE = "userLoginState";
    /**
     *
     * 用户角色 0 普通用户
     */
    int DEFAULT_ROLE = 0;
    /**
     *
     * 用户角色 1 管理员
     */
    int ADMIN_ROLE = 1;
}
