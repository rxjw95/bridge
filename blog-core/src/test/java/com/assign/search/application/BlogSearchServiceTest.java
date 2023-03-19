package com.assign.search.application;

import com.assign.search.application.out.api.SearchClient;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KakaoSearchApiResponse;
import com.assign.search.dto.response.KeywordSearchResponse;
import com.assign.search.vo.BlogDocument;
import com.assign.search.vo.PageInfo;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BlogSearchServiceTest {

    private final BlogSearchService subject;

    BlogSearchServiceTest() {
        this.subject = new BlogSearchService(new SearchClientStub());
    }

    @Test
    void search() {
        KeywordSearchRequest request = createKeywordSearchRequest();

        KeywordSearchResponse response = subject.search(request);

        Assertions.assertThat(response).isNotNull();
    }

    private KeywordSearchRequest createKeywordSearchRequest() {
        return new KeywordSearchRequest("keyword", null, null, null);
    }

    private static class SearchClientStub implements SearchClient {

        @Override
        public KakaoSearchApiResponse fetch(KeywordSearchRequest request) {
            BlogDocument blogDocument = BlogDocument.of(
                "블로그 명",
                "블로그 컨텐츠",
                LocalDateTime.of(2023, 3, 18, 10, 0),
                "https://search3.kakaocdn.net",
                "게시글 제목",
                "https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog");
            List<BlogDocument> documents = List.of(blogDocument);
            PageInfo pageInfo = new PageInfo(100, 50, false);
            return new KakaoSearchApiResponse(documents, pageInfo);
        }
    }
}