package com.assign.search.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.assign.search.application.out.api.SearchClient;
import com.assign.search.application.out.event.SearchEventPublisher;
import com.assign.search.dto.BlogDocument;
import com.assign.search.dto.PageInfo;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlogSearchServiceTest {

    private final SearchKeywordService subject;

    BlogSearchServiceTest() {
        this.subject = new SearchKeywordService(
            new SearchClientStub(),
            new SearchKeywordPublisherStub());
    }

    @Test
    void search() {
        KeywordSearchRequest request = createKeywordSearchRequest();

        KeywordSearchResponse response = subject.search(request);
        assertThat(response).isNotNull();
    }

    private KeywordSearchRequest createKeywordSearchRequest() {
        return new KeywordSearchRequest("keyword", null, null, null);
    }

    private static class SearchClientStub implements SearchClient {

        @Override
        public KeywordSearchResponse fetch(KeywordSearchRequest request) {
            BlogDocument blogDocument = BlogDocument.of(
                "블로그 명",
                "블로그 컨텐츠",
                "2023-03-20T18:50:07.000+09:00",
                "https://search3.kakaocdn.net",
                "게시글 제목",
                "https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog");
            List<BlogDocument> documents = List.of(blogDocument);
            PageInfo pageInfo = new PageInfo(100, 50, false);

            return new KeywordSearchResponse(documents, pageInfo);
        }
    }

    private static class SearchKeywordPublisherStub implements SearchEventPublisher {

        @Override
        public void publish(String keyword) {
            // anything
        }
    }
}