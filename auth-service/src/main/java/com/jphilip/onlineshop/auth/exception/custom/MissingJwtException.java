package com.jphilip.onlineshop.auth.exception.custom;

import io.jsonwebtoken.JwtException;

public class MissingJwtException extends JwtException {

    public MissingJwtException(String message) {
        super(message);
    }

    public MissingJwtException() {
        this("");
    }

    public MissingJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
