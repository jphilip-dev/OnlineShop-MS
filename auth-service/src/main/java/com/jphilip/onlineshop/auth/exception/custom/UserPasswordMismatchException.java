package com.jphilip.onlineshop.auth.exception.custom;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;

public class UserPasswordMismatchException extends UserException {

    public UserPasswordMismatchException(ErrorCodeConfig.ErrorDetail errorDetail, String message) {
        super(errorDetail, message);
    }

    public UserPasswordMismatchException(ErrorCodeConfig.ErrorDetail errorDetail) {
        super(errorDetail);
    }
}
