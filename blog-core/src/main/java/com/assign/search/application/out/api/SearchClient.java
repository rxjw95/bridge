package com.assign.search.application.out.api;


import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KakaoSearchApiResponse;

public interface SearchClient {

    KakaoSearchApiResponse fetch(KeywordSearchRequest request);

}
