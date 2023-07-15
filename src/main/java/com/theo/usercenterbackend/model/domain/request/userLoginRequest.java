package com.theo.usercenterbackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Theo
 * @version 1.0
 * Create by 2023/7/15 18:11
 */
@Data
public class userLoginRequest implements Serializable {
    private static final long serialVersionUID = -112745808329651071L;
    /**
     * 用户账户
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
}
