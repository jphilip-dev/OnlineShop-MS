package com.jphilip.onlineshop.auth.exception.custom;

public class UserNotFoundException extends UserException {

    public UserNotFoundException(String errorCode, Integer statusCode) {
        super(errorCode, statusCode);
    }

    public UserNotFoundException(String errorCode, Integer statusCode, String message) {
        super(errorCode, statusCode, message);
    }
}
