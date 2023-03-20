package com.assign.search.dto;

import lombok.Getter;

@Getter
public class PageInfo {

    private int totalCount;
    private int pageableCount;
    private boolean isLastPage;

    public PageInfo(int totalCount, int pageableCount, boolean isLastPage) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isLastPage = isLastPage;
    }

    protected PageInfo() {
    }
}
