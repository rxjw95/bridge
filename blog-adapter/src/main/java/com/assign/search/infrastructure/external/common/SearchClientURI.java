package com.assign.search.infrastructure.external.common;


import static com.assign.search.infrastructure.external.common.SearchQueryParam.KAKAO;
import static com.assign.search.infrastructure.external.common.SearchQueryParam.NAVER;

import java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;

public enum SearchClientURI {
    KAKAO_URI {
        @Override
        public URI toURI(String url, String query, String size, String page, String sort) {
            return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(KAKAO.getQuery(), query)
                .queryParam(KAKAO.getSize(), size)
                .queryParam(KAKAO.getPage(), page)
                .queryParam(KAKAO.getSort(), sort).encode().build().toUri();
        }
    },
    NAVER_URI {
        @Override
        public URI toURI(String url, String query, String size, String page, String sort) {
            return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(NAVER.getQuery(), query)
                .queryParam(NAVER.getSize(), size)
                .queryParam(NAVER.getPage(), page)
                .queryParam(NAVER.getSort(), sort).encode().build().toUri();
        }
    };

    public abstract URI toURI(String url, String query, String size, String page, String sort);

}
