package com.assign.search.infrastructure.external.dto;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverSearchResponse {

    private int total;
    private int start;
    private int display;
    private List<Map<String, String>> items;
}
