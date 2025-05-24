package com.jphilip.onlineshop.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ExceptionResponseDTO(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<String, String> fieldErrors,
        String path) {
}
