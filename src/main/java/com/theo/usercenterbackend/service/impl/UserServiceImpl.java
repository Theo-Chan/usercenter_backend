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
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.theo.usercenterbackend.constant.userConstant.USER_LOGIN_STATE;

/**
* @author adbam
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-07-14 14:15:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    private static final String SALT ="Theo";
    @Resource
    private UserMapper userMapper;
    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode) {
        //非空校验
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode))
        {
            return -1L;
        }
        //用户名校验
        if (userAccount.length() < 4){
            return -1L;
        }

        if (planetCode.length() > 5){
            return -1L;
        }
        //用户名特殊字符校验
        //todo 还没测试
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return -1L;
        }
        //密码与确认密码校验
        if (!userPassword.equals(checkPassword)){
            return -1L;
        }
        //密码长度校验
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

        //星球编号重复校验
        userQueryWrapper = new QueryWrapper<>(null);
        userQueryWrapper.eq("planetCode", planetCode);
        count = userMapper.selectCount(userQueryWrapper);
        if (count > 0) {
            return -1L;
        }

        //密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);
        boolean saveResult = this.save(user);
        if (!saveResult){
            return -1L;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //非空校验
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        //用户名特殊字符校验
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return null;
        }
        //密码长度校验
        if (userPassword.length() < 8 ){
            return null;
        }
        //密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //构造查询条件
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount",userAccount);
        userQueryWrapper.eq("userPassword",encryptPassword);
        //查询数据库
        User user = userMapper.selectOne(userQueryWrapper);
        //查询结果判断
        if (user == null){
            return null;
        }
        //用户数据脱敏
        User safetyUser = getSafetyUser(user);
        //记录登录状态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);
        //返回安全用户信息
        return safetyUser;
    }

    /**
     * 用户信息脱敏
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser){
        //需要对传进来的值进行判空
        if (originUser == null){
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUserName(originUser.getUserName());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setPlanetCode(originUser.getPlanetCode());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        return safetyUser;
    }

    @Override
    public int userLogOut(HttpServletRequest request) {
        request.removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}




