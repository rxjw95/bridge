package com.assign.search.infrastructure.external;

import org.springframework.stereotype.Component;

@Component
public class SearchRequestConverter {

    public String toNaverSortQuery(String sort) {
        return "accuracy".equals(sort) ? "sim" : "date";
    }


}
