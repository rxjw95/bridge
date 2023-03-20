package com.assign.search.application;

import com.assign.search.application.in.usecase.SearchKeywordUseCase;
import com.assign.search.application.out.api.SearchClient;
import com.assign.search.application.out.event.SearchEventPublisher;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class SearchKeywordService implements SearchKeywordUseCase {

    private final SearchClient searchClient;
    private final SearchEventPublisher publisher;

    public SearchKeywordService(SearchClient searchClient, SearchEventPublisher publisher) {
        this.searchClient = searchClient;
        this.publisher = publisher;
    }

    @Override
    public KeywordSearchResponse search(KeywordSearchRequest request) {
        publisher.publish(request.getQuery());
        return searchClient.fetch(request);
    }


}
