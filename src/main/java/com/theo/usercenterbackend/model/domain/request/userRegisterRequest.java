package com.theo.usercenterbackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Theo
 * @version 1.0
 * Create by 2023/7/15 17:44
 */
@Data
public class userRegisterRequest implements Serializable{

    private static final long serialVersionUID = -8626220038894364565L;
    /**
     * 用户账户
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 确认密码
     */
    private String checkPassword;
}
