package com.assign.search.infrastructure.external.common;

import lombok.Getter;

@Getter
public enum ParameterKey {
    KAKAO("query", "size", "page", "sort"),
    NAVER("query", "display", "start", "sort");

    private final String query;
    private final String size;
    private final String page;
    private final String sort;

    ParameterKey(String query, String size, String page, String sort) {
        this.query = query;
        this.size = size;
        this.page = page;
        this.sort = sort;
    }


}
