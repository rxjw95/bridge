package com.assign.search.exception;

import lombok.Getter;

@Getter
public class SearchClientException extends RuntimeException {

    private final ErrorCode errorCode;

    public SearchClientException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
