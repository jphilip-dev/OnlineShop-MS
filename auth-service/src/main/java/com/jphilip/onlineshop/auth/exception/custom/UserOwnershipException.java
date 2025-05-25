package com.jphilip.onlineshop.auth.exception.custom;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import com.jphilip.onlineshop.auth.entity.User;

public class UserOwnershipException extends UserException {

    public UserOwnershipException(ErrorCodeConfig.ErrorDetail errorDetail, String message) {
        super(errorDetail, message);
    }

    public UserOwnershipException(ErrorCodeConfig.ErrorDetail errorDetail) {
        super(errorDetail);
    }
}
