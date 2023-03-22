package com.assign.search.application.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버 내부에서 오류가 발생했습니다."),
    EXTERNAL_SEARCH_API_ERROR(500, "검색 API 서버에서 오류가 발생했습니다."),
    INVALID_INPUT_VALUE(400, "적절하지 않은 요청 값입니다.");

    private final String message;
    private final int status;

    ErrorCode(int status, String message) {
        this.message = message;
        this.status = status;
    }

}