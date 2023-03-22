package com.assign.search.infrastructure.external;

import com.assign.search.application.exception.ErrorCode;
import com.assign.search.application.exception.SearchClientException;
import com.assign.search.application.out.api.SearchClient;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Primary
public class DetourSearchClients implements SearchClient {

    private final RetryTemplate retryTemplate;
    private final KakaoSearchClient kakaoSearchClient;
    private final NaverSearchClient naverSearchClient;

    @Override
    public KeywordSearchResponse fetch(KeywordSearchRequest request) {
        try {
            return retryTemplate.execute(
                context -> kakaoSearchClient.fetch(request));
        } catch (Exception e) {
            try {
                return retryTemplate.execute(
                    context -> naverSearchClient.fetch(request));
            } catch (Exception ex) {
                throw new SearchClientException(ErrorCode.EXTERNAL_SEARCH_API_ERROR);
            }
        }
    }
}
