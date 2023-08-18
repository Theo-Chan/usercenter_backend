package com.theo.usercenterbackend.exception;

import com.theo.usercenterbackend.common.ErrorCode;
import lombok.Data;
import org.springframework.boot.web.server.ErrorPage;

/**
 * @author Theo
 * @version 1.0
 * Create by 2023/8/18 15:01
 */

public class BusinessException extends RuntimeException{
    private final int code;
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode,String description){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
