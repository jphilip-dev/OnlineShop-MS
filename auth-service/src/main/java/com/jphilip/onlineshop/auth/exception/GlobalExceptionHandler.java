package com.jphilip.onlineshop.auth.exception;

import com.jphilip.onlineshop.auth.config.ErrorCodeConfig;
import com.jphilip.onlineshop.auth.dto.ExceptionResponseDTO;
import com.jphilip.onlineshop.auth.exception.custom.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorCodeConfig errorCodeConfig;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleOtherException(Exception ex, WebRequest request) {

        log.error("Unhandled error: {}", ex.getMessage(), ex);

        var exception = new UserException(errorCodeConfig.getAppError());

        return createResponse(exception.getHttpStatus(), exception.getErrorMessage(), request);
    }

    /*
     *
     *  Helper method/s
     *
     */

    private ResponseEntity<ExceptionResponseDTO> createResponse(
            HttpStatus httpStatus,
            String errorMessage,
            WebRequest request) {

        return ResponseEntity.status(httpStatus)
                .body(ExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .status(httpStatus.value())
                        .error(httpStatus.getReasonPhrase())
                        .message(errorMessage)
                        .path(request.getDescription(false).replace("uri=", ""))
                        .build());

    }
}