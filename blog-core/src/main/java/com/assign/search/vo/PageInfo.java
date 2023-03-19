package com.assign.search.vo;

import lombok.Getter;

@Getter
public class PageInfo {

  private final int totalCount;
  private final int pageableCount;
  private final boolean isLastPage;

  public PageInfo(int totalCount, int pageableCount, boolean isLastPage) {
    this.totalCount = totalCount;
    this.pageableCount = pageableCount;
    this.isLastPage = isLastPage;
  }
}
