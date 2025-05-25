package com.jphilip.onlineshop.auth.exception.custom;

import com.jphilip.onlineshop.auth.entity.User;

public class UserOwnershipException extends UserException {
    public UserOwnershipException(String errorCode, Integer statusCode) {
        super(errorCode, statusCode);
    }

    public UserOwnershipException(String errorCode, Integer statusCode, String message) {
        super(errorCode, statusCode, message);
    }
}
