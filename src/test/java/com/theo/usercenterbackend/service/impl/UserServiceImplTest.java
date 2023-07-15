package com.theo.usercenterbackend.service.impl;

import com.theo.usercenterbackend.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class UserServiceImplTest {
    @Resource
    private UserServiceImpl userService;
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUserName("Theo");
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

    @Test
    void userRegister() {
        String Accuont = "fake_Chan_1";
        String PassWord = "12345678";
        String checkPassWord = "12345678";
        Long result = userService.userRegister(Accuont, PassWord, checkPassWord);
        Assertions.assertEquals(2,result);
    }

    @Test
    void userLogin() {
    }
}