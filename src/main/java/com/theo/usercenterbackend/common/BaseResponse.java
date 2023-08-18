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

    private static final long serialVersionUID = -8983208146934227762L;

    private Integer code;
    private T data;
    private String message;
    private String description;

    public BaseResponse(Integer code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }
    public BaseResponse(Integer code, T data) {
        this(code,data,"","");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(),null, errorCode.getMessage(), errorCode.getDescription());
    }

    public BaseResponse(ErrorCode errorCode,String description) {
        this(errorCode.getCode(),null, errorCode.getMessage(),description);
    }

    public BaseResponse(ErrorCode errorCode,String message,String description) {
        this(errorCode.getCode(),null, message,description);
    }

    public BaseResponse(int errorCode,String message,String description) {
        this(errorCode,null,message,description);
    }

}
