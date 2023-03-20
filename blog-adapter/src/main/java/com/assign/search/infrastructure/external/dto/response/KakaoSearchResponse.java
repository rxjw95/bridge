package com.assign.search.infrastructure.external.dto.response;

import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class KakaoSearchResponse {

    private List<Map<String, String>> documents;
    private Map<String, String> meta;

    public KakaoSearchResponse(List<Map<String, String>> documents, Map<String, String> meta) {
        this.documents = documents;
        this.meta = meta;
    }

    protected KakaoSearchResponse() {
    }
}
