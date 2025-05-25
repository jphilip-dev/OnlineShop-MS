package com.jphilip.onlineshop.auth.exception.custom;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import lombok.Getter;

@Getter
public class UserException extends BaseException {

    public UserException(ErrorCodeConfig.ErrorDetail errorDetail, String message) {
        super(errorDetail, message);
    }

    public UserException(ErrorCodeConfig.ErrorDetail errorDetail) {
        super(errorDetail);
    }
}
