package com.jphilip.onlineshop.auth.exception.custom;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public abstract class BaseException extends RuntimeException {

    private HttpStatus httpStatus;
    private final String errorMessage;

    public BaseException(ErrorCodeConfig.ErrorDetail errorDetail, String message){
        super();
        this.errorMessage = errorDetail.getMessage() + (message.isBlank() ? "" : message);
        try {
            this.httpStatus = HttpStatus.valueOf(errorDetail.getStatusCode());
        } catch (Exception ex){

            log.warn("Invalid status code: {}", errorDetail.getStatusCode());

            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public BaseException(ErrorCodeConfig.ErrorDetail errorDetail){
        this(errorDetail, "");
    }


}
