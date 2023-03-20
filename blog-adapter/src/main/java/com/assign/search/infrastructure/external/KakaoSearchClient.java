package com.assign.search.infrastructure.external;

import static com.assign.search.infrastructure.external.common.ParameterKey.KAKAO;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.GET;

import com.assign.search.application.out.api.SearchClient;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import com.assign.search.infrastructure.external.dto.response.KakaoSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KakaoSearchClient implements SearchClient {

    private final RestTemplate restTemplate;
    private final SearchResponseConverter converter;

    @Value("${kakao.api}")
    private String kakaoApiUrl;
    @Value("${kakao.rest-key}")
    private String kakakRestKey;

    public KakaoSearchClient(RestTemplate restTemplate, SearchResponseConverter converter) {
        this.restTemplate = restTemplate;
        this.converter = converter;
    }

    @Override
    public KeywordSearchResponse fetch(KeywordSearchRequest request) {
        HttpEntity<Object> httpEntity = getHttpEntityContainsHeaders();
        String url = getUrl(request);

        // TODO. exception 처리
        KakaoSearchResponse response = restTemplate.exchange(
                url,
                GET,
                httpEntity,
                KakaoSearchResponse.class)
            .getBody();

        return converter.convert(response);
    }

    private String getUrl(KeywordSearchRequest request) {
        return UriComponentsBuilder.fromHttpUrl(kakaoApiUrl)
            .queryParam(KAKAO.getQuery(), request.getQuery())
            .queryParam(KAKAO.getSize(), request.getSize().toString())
            .queryParam(KAKAO.getPage(), request.getPage().toString())
            .queryParam(KAKAO.getSort(), request.getSort()).encode(UTF_8).toUriString();
    }

    private HttpEntity<Object> getHttpEntityContainsHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION, kakakRestKey);
        return new HttpEntity<>(httpHeaders);
    }


}
