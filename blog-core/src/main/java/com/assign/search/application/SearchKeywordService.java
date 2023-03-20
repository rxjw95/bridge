package com.assign.search.application;

import com.assign.search.application.in.usecase.SearchKeywordUseCase;
import com.assign.search.application.out.api.SearchClient;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class SearchKeywordService implements SearchKeywordUseCase {

    private final SearchClient searchClient;

    public SearchKeywordService(SearchClient searchClient) {
        this.searchClient = searchClient;
    }

    @Override
    public KeywordSearchResponse search(KeywordSearchRequest request) {
        return searchClient.fetch(request);
    }


}
