package com.assign.search.infrastructure.external;

import static com.assign.search.application.exception.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.assign.search.infrastructure.external.common.SearchClientHeader.NAVER_HEADER;
import static com.assign.search.infrastructure.external.common.SearchClientURI.NAVER_URI;
import static org.springframework.http.HttpMethod.GET;

import com.assign.search.application.exception.ResponseBodyEmptyException;
import com.assign.search.application.out.api.SearchClient;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import com.assign.search.infrastructure.external.dto.NaverSearchResponse;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class NaverSearchClient implements SearchClient {

    private final RestTemplate restTemplate;
    private final SearchResponseConverter responseConverter;
    private final SearchRequestConverter requestConverter;

    @Value("${naver.api}")
    private String naverApiUrl;

    @Value("${naver.client-id}")
    private String naverClientKey;

    @Value("${naver.client-secret-id}")
    private String naverClientSecretKey;

    private final static String CLIENT_KEY_HEADER = "X-Naver-Client-Id";
    private final static String CLIENT_SECRET_KEY_HEADER = "X-Naver-Client-Secret";

    @Override
    public KeywordSearchResponse fetch(KeywordSearchRequest request) {
        HttpEntity<HttpHeaders> httpEntity = NAVER_HEADER.toHttpEntity(
            List.of(Pair.of(CLIENT_KEY_HEADER, naverClientKey),
                Pair.of(CLIENT_SECRET_KEY_HEADER, naverClientSecretKey))
        );

        URI uri = NAVER_URI.toURI(
            naverApiUrl,
            request.getQuery(),
            request.getSize().toString(), request.getPage().toString(),
            requestConverter.toNaverSortQuery(request.getSort())
        );

        ResponseEntity<NaverSearchResponse> response = restTemplate.exchange(
            uri,
            GET,
            httpEntity,
            NaverSearchResponse.class);

        return responseConverter.convert(extractBody(response));
    }

    private NaverSearchResponse extractBody(ResponseEntity<NaverSearchResponse> response) {
        if (response.getBody() == null) {
            throw new ResponseBodyEmptyException(INTERNAL_SERVER_ERROR);
        }
        return response.getBody();
    }
}
