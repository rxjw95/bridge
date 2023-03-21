package com.assign.search.infrastructure.external.common;

import java.util.List;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public enum SearchClientHeader {

    KAKAO_HEADER {
        @Override
        public final HttpEntity<HttpHeaders> toHttpEntity(List<Pair<String, String>> entries) {
            return generate(entries);
        }
    },
    NAVER_HEADER {
        @Override
        public final HttpEntity<HttpHeaders> toHttpEntity(List<Pair<String, String>> entries) {
            return generate(entries);
        }
    };

    public abstract HttpEntity<HttpHeaders> toHttpEntity(List<Pair<String, String>> entries);

    private static HttpEntity<HttpHeaders> generate(List<Pair<String, String>> entries) {
        HttpHeaders headers = new HttpHeaders();
        entries.forEach(entry -> headers.add(entry.getFirst(), entry.getSecond()));
        return new HttpEntity<>(headers);
    }
}
