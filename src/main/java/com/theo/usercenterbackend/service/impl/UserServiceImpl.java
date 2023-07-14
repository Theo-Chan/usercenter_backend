package com.theo.usercenterbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theo.usercenterbackend.model.domain.User;
import com.theo.usercenterbackend.service.UserService;
import com.theo.usercenterbackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author adbam
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-07-14 14:15:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




