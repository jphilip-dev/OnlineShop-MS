package com.jphilip.onlineshop.auth.exception.custom;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public abstract class BaseException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorMessage;

    public BaseException(ErrorCodeConfig.ErrorDetail errorDetail, String message){
        super();
        this.errorMessage = errorDetail.getMessage() + (message.isBlank() ? "" : message);
        HttpStatus status;

        try {
            status = HttpStatus.valueOf(errorDetail.getStatusCode());
        } catch (Exception ex) {
            log.warn("Invalid HTTP status code [{}] for error message '{}'. Defaulting to 500.",
                    errorDetail.getStatusCode(), errorDetail.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        this.httpStatus = status;
    }

    public BaseException(ErrorCodeConfig.ErrorDetail errorDetail){
        this(errorDetail, "");
    }

}
