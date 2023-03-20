package com.assign.search.application.out.api;


import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;

public interface SearchClient {

    KeywordSearchResponse fetch(KeywordSearchRequest request);

}
