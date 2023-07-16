package com.theo.usercenterbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.theo.usercenterbackend.model.domain.User;
import com.theo.usercenterbackend.model.domain.request.userLoginRequest;
import com.theo.usercenterbackend.model.domain.request.userRegisterRequest;
import com.theo.usercenterbackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.theo.usercenterbackend.constant.userConstant.USER_LOGIN_STATE;

/**
 * @author Theo
 * @version 1.0
 * Create by 2023/7/15 17:39
 */
@RestController
@RequestMapping("/user")
public class userController {
    @Resource
    UserService userService;
    @PostMapping("/register")
    public Long userRegister(@RequestBody userRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        Long userId = userService.userRegister(userAccount, userPassword, checkPassword);
        return userId;
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody userLoginRequest userloginrequest, HttpServletRequest request){
        if (userloginrequest == null){
            return null;
        }
        String userAccount = userloginrequest.getUserAccount();
        String userPassword = userloginrequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return  userService.userLogin(userAccount, userPassword,request);
    }

    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request){
        //获取前端session
        Object attribute = request.getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) attribute;
        if (currentUser == null){
            return null;
        }
        //todo 没有对用户的状态判断
        //这里可以选择直接从缓存中获取用户信息也可以查询数据库 但是处于时效性的考虑选择查库
        User user = userService.getById(currentUser.getId());
        return userService.getSafetyUser(user);
    }

    @PostMapping("/search")
    public List<User> searchUser(String userName,HttpServletRequest request){
        //todo 返回类型问题
        if (!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //接收userName
        if (StringUtils.isNotBlank(userName)){
            userQueryWrapper.like("userName",userName);
        }
        List<User> userList = userService.list(userQueryWrapper);
        //Java 8 新特性
        return userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
    }

    /**
     *
     * 删除需要管理员权限
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public boolean searchUser(Long id,HttpServletRequest request){
        if (!isAdmin(request)){
            return false;
        }
        if (id <= 0){
            return false;
        }
        //接收userName
       return userService.removeById(id);
    }

    /**
     * 鉴权函数
     */
    private boolean isAdmin(HttpServletRequest request){
        //todo 搞清楚存的是什么
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null || user.getUserRole() != 1){
            return false;
        }
        return true;
    }
}
