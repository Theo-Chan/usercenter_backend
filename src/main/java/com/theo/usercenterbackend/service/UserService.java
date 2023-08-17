package com.theo.usercenterbackend.service;

import com.theo.usercenterbackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author adbam
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-07-14 14:15:21
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户昵称
     * @param userPassword  用户密码
     * @param checkPassword 确认密码
     * @param planetCode    星球编号
     * @return user
     */
    Long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

    /**
     * 用户登录
     * @param userAccount 用户昵称
     * @param userPassword 用户密码
     * @param request  存session
     * @return   用户脱敏信息 safetyUser
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return safetyUser
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param request  session
     * @return
     */
    int userLogOut(HttpServletRequest request);
}
