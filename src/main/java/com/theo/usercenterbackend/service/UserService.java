package com.theo.usercenterbackend.service;

import com.theo.usercenterbackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author adbam
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-07-14 14:15:21
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 用户昵称
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return user
     */
    Long userRegister(String userAccount,String userPassword,String checkPassword);
}
