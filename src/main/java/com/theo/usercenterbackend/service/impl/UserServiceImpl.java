package com.theo.usercenterbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theo.usercenterbackend.model.domain.User;
import com.theo.usercenterbackend.service.UserService;
import com.theo.usercenterbackend.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author adbam
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-07-14 14:15:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        //非空校验
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword))
        {
            return -1L;
        }
        //用户名校验
        if (userAccount.length() < 4){
            return -1L;
        }
        //用户名特殊字符校验
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return -1L;
        }
        //密码与确认密码校验
        if (!userPassword.equals(checkPassword)){
            return -1L;
        }
        //密码校验
        if (userPassword.length() < 8 ){
            return -1L;
        }
        //用户名重复校验
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(null);
        userQueryWrapper.eq("userAccount", userAccount);
        Long count = userMapper.selectCount(userQueryWrapper);
        if (count > 0){
            return -1L;
        }
        //密码加密
        final String SALT ="Theo" ;
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult){
            return -1L;
        }
        return user.getId();
    }
}




