package com.theo.usercenterbackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 * @author Theo
 * @version 1.0
 * Create by 2023/8/17 23:19
 */
@Data
public class BaseResponse<T> implements Serializable {
    private Integer code;
    private T data;
    private String message;

    public BaseResponse(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public BaseResponse(Integer code, T data) {
        this(code,data,"");
    }

}
