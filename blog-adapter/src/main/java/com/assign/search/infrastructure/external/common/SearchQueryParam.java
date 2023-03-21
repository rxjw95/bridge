package com.assign.search.infrastructure.external.common;

import lombok.Getter;

@Getter
enum SearchQueryParam {
    KAKAO("query", "size", "page", "sort"),
    NAVER("query", "display", "start", "sort");

    private final String query;
    private final String size;
    private final String page;
    private final String sort;

    SearchQueryParam(String query, String size, String page, String sort) {
        this.query = query;
        this.size = size;
        this.page = page;
        this.sort = sort;
    }
}
