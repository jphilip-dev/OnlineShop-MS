package com.jphilip.onlineshop.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "custom.errors")
@Data
public class ErrorCodeConfig {
    private int userNotFoundCode;
    private String userNotFoundMessage;

    private int unauthorizedCode;
    private String unauthorizedMessage;

    private int validationFailedCode;
    private String validationFailedMessage;
}