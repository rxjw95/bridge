package com.assign.search.exception;

import lombok.Getter;

@Getter
public class ResponseBodyEmptyException extends RuntimeException {

    private final ErrorCode errorCode;

    public ResponseBodyEmptyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
