package com.assign.search.infrastructure.external;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

import com.assign.search.dto.BlogDocument;
import com.assign.search.dto.PageInfo;
import com.assign.search.dto.response.KeywordSearchResponse;
import com.assign.search.infrastructure.external.dto.response.KakaoSearchResponse;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class SearchResponseConverter {

    public KeywordSearchResponse convert(KakaoSearchResponse kakaoResponse) {
        List<BlogDocument> blogDocuments = makeBlogDocument(kakaoResponse.getDocuments());
        PageInfo pageInfo = makePageInfo(kakaoResponse.getMeta());
        return new KeywordSearchResponse(blogDocuments, pageInfo);
    }

    private List<BlogDocument> makeBlogDocument(List<Map<String, String>> documents) {
        return documents.stream()
            .map(mapToDocumentFunc())
            .toList();
    }

    private Function<Map<String, String>, BlogDocument> mapToDocumentFunc() {
        return table -> BlogDocument.of(
            table.get("blogname"),
            table.get("contents"),
            table.get("datetime"),
            table.get("thumbnail"),
            table.get("title"),
            table.get("url"));
    }

    private PageInfo makePageInfo(Map<String, String> meta) {
        return new PageInfo(
            parseInt(meta.get("total_count")),
            parseInt(meta.get("pageable_count")),
            parseBoolean(meta.get("is_end")));
    }

}
