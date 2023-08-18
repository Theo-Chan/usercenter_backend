package com.theo.usercenterbackend.common;

/**
 * @author Theo
 * @version 1.0
 * Create by 2023/8/17 23:32
 */

/**
 * 包装通用返回类
 */
public class ResultUtils {
    //todo 泛型
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "ok","");
    }

    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }
    public static BaseResponse error(ErrorCode errorCode,String description){
        return new BaseResponse<>(errorCode,description);
    }

    public static BaseResponse error(ErrorCode errorCode,String message,String description){
        return new BaseResponse<>(errorCode,message,description);
    }

    public static BaseResponse error(int errorCode,String message,String description){
        return new BaseResponse<>(errorCode,message,description);
    }

}
