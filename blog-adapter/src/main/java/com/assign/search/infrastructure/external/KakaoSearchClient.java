package com.assign.search.infrastructure.external;

import static com.assign.search.application.exception.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.assign.search.infrastructure.external.common.SearchClientHeader.KAKAO_HEADER;
import static com.assign.search.infrastructure.external.common.SearchClientURI.KAKAO_URI;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.GET;

import com.assign.search.application.exception.ResponseBodyEmptyException;
import com.assign.search.application.out.api.SearchClient;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import com.assign.search.infrastructure.external.dto.KakaoSearchResponse;
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
public class KakaoSearchClient implements SearchClient {

    private final RestTemplate restTemplate;
    private final SearchResponseConverter converter;

    @Value("${kakao.api}")
    private String kakaoApiUrl;

    @Value("${kakao.rest-key}")
    private String kakaoRestKey;

    @Override
    public KeywordSearchResponse fetch(KeywordSearchRequest request) {
        HttpEntity<HttpHeaders> httpEntity = KAKAO_HEADER.toHttpEntity(
            List.of(Pair.of(AUTHORIZATION, kakaoRestKey)));

        URI uri = KAKAO_URI.toURI(
            kakaoApiUrl,
            request.getQuery(),
            request.getSize().toString(), request.getPage().toString(),
            request.getSort());

        ResponseEntity<KakaoSearchResponse> response = restTemplate.exchange(
            uri,
            GET,
            httpEntity,
            KakaoSearchResponse.class);

        return converter.convert(extractBody(response));
    }

    private KakaoSearchResponse extractBody(ResponseEntity<KakaoSearchResponse> response) {
        if (response.getBody() == null) {
            throw new ResponseBodyEmptyException(INTERNAL_SERVER_ERROR);
        }
        return response.getBody();
    }

}
