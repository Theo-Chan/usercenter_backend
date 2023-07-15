package com.theo.usercenterbackend.controller;

import com.sun.scenario.effect.impl.prism.ps.PPSBlend_LIGHTENPeer;
import com.theo.usercenterbackend.model.domain.User;
import com.theo.usercenterbackend.model.domain.request.userLoginRequest;
import com.theo.usercenterbackend.model.domain.request.userRegisterRequest;
import com.theo.usercenterbackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.idn.Punycode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
}
