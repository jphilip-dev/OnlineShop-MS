package com.jphilip.onlineshop.auth.exception.custom;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public abstract class BaseException extends RuntimeException {

    private final String errorCode;
    private HttpStatus httpStatus;

    public BaseException(String errorCode, Integer statusCode) {
        super();
        this.errorCode = errorCode;
        try {
            this.httpStatus = HttpStatus.valueOf(statusCode);
        } catch (Exception ex){
            this.httpStatus = handleStatusCodeErr(statusCode);
        }

    }

    public BaseException(String errorCode, Integer statusCode, String message) {
        super(message);
        this.errorCode = errorCode;
        try {
            this.httpStatus = HttpStatus.valueOf(statusCode);
        } catch (Exception ex){
            this.httpStatus = handleStatusCodeErr(statusCode);
        }
    }

    private HttpStatus handleStatusCodeErr(Integer statusCode){
        log.warn("Invalid status code: {}", statusCode);
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
