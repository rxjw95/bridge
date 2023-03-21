package com.assign.search.infrastructure.external.dto;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoSearchResponse {

    private List<Map<String, String>> documents;
    private Map<String, String> meta;
}
