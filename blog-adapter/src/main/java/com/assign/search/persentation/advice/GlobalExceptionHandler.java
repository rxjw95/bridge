package com.assign.search.persentation.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.assign.search.application.exception.ErrorCode;
import com.assign.search.application.exception.SearchClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        ErrorResponse response = ErrorResponse.of(
            ErrorCode.INVALID_INPUT_VALUE,
            e.getBindingResult());

        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(SearchClientException.class)
    public ResponseEntity<ErrorResponse> handleSearchClientException(SearchClientException e) {
        ErrorResponse response = ErrorResponse.from(e.getErrorCode());

        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unknown Errors: {}", e.getMessage());
        ErrorResponse response = ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }
}
