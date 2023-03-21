package com.assign.search.infrastructure.external;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

import com.assign.search.dto.BlogDocument;
import com.assign.search.dto.PageInfo;
import com.assign.search.dto.response.KeywordSearchResponse;
import com.assign.search.infrastructure.external.dto.KakaoSearchResponse;
import com.assign.search.infrastructure.external.dto.NaverSearchResponse;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class SearchResponseConverter {

    public KeywordSearchResponse convert(KakaoSearchResponse kakaoResponse) {
        List<BlogDocument> blogDocuments = makeKakaoBlogDocument(kakaoResponse.getDocuments());
        PageInfo pageInfo = makeKakaoPageInfo(kakaoResponse.getMeta());
        return new KeywordSearchResponse(blogDocuments, pageInfo);
    }

    public KeywordSearchResponse convert(NaverSearchResponse naverResponse) {
        List<BlogDocument> blogDocuments = makeNaverBlogDocument(naverResponse.getItems());
        PageInfo pageInfo = makeNaverPageInfo(
            naverResponse.getTotal(),
            naverResponse.getDisplay(),
            naverResponse.getStart());

        return new KeywordSearchResponse(blogDocuments, pageInfo);
    }

    private List<BlogDocument> makeNaverBlogDocument(List<Map<String, String>> documents) {
        return documents.stream()
            .map(mapToNaverDocumentFunc())
            .toList();
    }

    private Function<Map<String, String>, BlogDocument> mapToNaverDocumentFunc() {
        return table -> BlogDocument.of(
            table.get("bloggername"),
            table.get("description"),
            table.get("postdate"),
            "",
            table.get("title"),
            table.get("link")
        );
    }

    private PageInfo makeNaverPageInfo(int total, int display, int start) {
        int limit = display * start;
        boolean isLastPage = (limit - display) < total && total < limit;
        return new PageInfo(
            total,
            null,
            isLastPage);
    }

    private List<BlogDocument> makeKakaoBlogDocument(List<Map<String, String>> documents) {
        return documents.stream()
            .map(mapToKakaoDocumentFunc())
            .toList();
    }

    private Function<Map<String, String>, BlogDocument> mapToKakaoDocumentFunc() {
        return table -> BlogDocument.of(
            table.get("blogname"),
            table.get("contents"),
            table.get("datetime"),
            table.get("thumbnail"),
            table.get("title"),
            table.get("url"));
    }

    private PageInfo makeKakaoPageInfo(Map<String, String> meta) {
        return new PageInfo(
            parseInt(meta.get("total_count")),
            parseInt(meta.get("pageable_count")),
            parseBoolean(meta.get("is_end")));
    }

}
