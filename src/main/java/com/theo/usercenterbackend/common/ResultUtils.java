package com.theo.usercenterbackend.common;

/**
 * @author Theo
 * @version 1.0
 * Create by 2023/8/17 23:32
 */
public class ResultUtils {
    //todo 泛型
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "ok");
    }
}
