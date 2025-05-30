package com.jphilip.onlineshop.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

//@Configuration
//@ConfigurationProperties(prefix = "custom.errors")
//@Data
//public class ErrorCodeConfig {
//    private int userNotFoundStatusCode;
//    private String userNotFoundMessage;
//
//    private int unauthorizedStatusCode;
//    private String unauthorizedMessage;
//
//    private int validationFailedStatusCode;
//    private String validationFailedMessage;
//
//    private int appErrorStatusCode;
//    private String appErrorMessage;
//
//}

@Configuration
@ConfigurationProperties(prefix = "custom.errors")
@Data
public class ErrorCodeConfig {

    private ErrorDetail unauthorized;
    private ErrorDetail notFound;
    private ErrorDetail validationFailed;
    private ErrorDetail appError;

    @Data
    public static class ErrorDetail {
        private int statusCode;
        private String message;
    }
}