package com.jphilip.onlineshop.auth.exception.custom;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;

public class UserNotFoundException extends UserException {


    public UserNotFoundException(ErrorCodeConfig.ErrorDetail errorDetail, String message) {
        super(errorDetail, message);
    }

    public UserNotFoundException(ErrorCodeConfig.ErrorDetail errorDetail) {
        super(errorDetail);
    }
}
