package com.assign.search.dto.response;

import com.assign.search.dto.BlogDocument;
import com.assign.search.dto.PageInfo;
import java.util.List;

public record KeywordSearchResponse(List<BlogDocument> documents, PageInfo pageInfo) {

}
