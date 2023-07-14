package com.theo.usercenterbackend.service.impl;
import java.util.Date;

import com.theo.usercenterbackend.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Resource
    private UserServiceImpl userService;
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("Theo");
        user.setUserAccount("12344");
        user.setAvatarUrl("https://img.yzcdn.cn/vant/logo.png");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("12345");
        user.setEmail("4546667");
        user.setUserStatus(0);
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);

    }
}