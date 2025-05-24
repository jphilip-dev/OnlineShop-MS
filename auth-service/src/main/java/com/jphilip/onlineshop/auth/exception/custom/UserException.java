package com.jphilip.onlineshop.auth.exception.custom;

import lombok.Getter;

@Getter
public abstract class UserException extends BaseException {

    public UserException(String errorCode, Integer statusCode) {
        super(errorCode, statusCode);
    }

    public UserException(String errorCode, Integer statusCode, String message) {
        super(errorCode, statusCode, message);
    }
}
