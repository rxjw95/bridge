package com.assign.search.application;

import com.assign.search.application.in.usecase.BlogSearchUseCase;
import com.assign.search.application.out.api.SearchClient;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KakaoSearchApiResponse;
import com.assign.search.dto.response.KeywordSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class BlogSearchService implements BlogSearchUseCase {

    private final SearchClient searchClient;

    public BlogSearchService(SearchClient searchClient) {
        this.searchClient = searchClient;
    }

    @Override
    public KeywordSearchResponse search(KeywordSearchRequest request) {
        KakaoSearchApiResponse externalResponse = searchClient.fetch(request);
        return new KeywordSearchResponse(
            externalResponse.getDocuments(),
            externalResponse.getMeta()
        );
    }


}
