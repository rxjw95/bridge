package com.assign.search.dto.response;

import com.assign.search.vo.BlogDocument;
import com.assign.search.vo.PageInfo;
import java.util.List;

public record KeywordSearchResponse(List<BlogDocument> documents, PageInfo pageInfo) {

}
