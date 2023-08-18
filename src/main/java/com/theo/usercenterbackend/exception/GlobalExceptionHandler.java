package com.theo.usercenterbackend.exception;

import com.theo.usercenterbackend.common.BaseResponse;
import com.theo.usercenterbackend.common.ErrorCode;
import com.theo.usercenterbackend.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.sun.webkit.perf.WCGraphicsPerfLogger.log;

/**
 * @author Theo
 * @version 1.0
 * Create by 2023/8/18 15:08
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e){
        log.error("businessException" + e);
        return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription());

    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e){
        log.error("RuntimeException" + e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }
}
