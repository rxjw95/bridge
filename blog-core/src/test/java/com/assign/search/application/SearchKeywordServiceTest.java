package com.assign.search.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.assign.search.application.out.api.SearchClient;
import com.assign.search.application.out.event.SearchEventPublisher;
import com.assign.search.dto.BlogDocument;
import com.assign.search.dto.PageInfo;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchKeywordServiceTest {

    @Mock
    private SearchClient searchClient;

    @Mock
    private SearchEventPublisher searchEventPublisher;

    @InjectMocks
    private SearchKeywordService subject;

    @Test
    void search() {
        KeywordSearchRequest request = createKeywordSearchRequest();
        KeywordSearchResponse response = createKeywordSearchResponse();
        given(searchClient.fetch(request)).willReturn(response);

        KeywordSearchResponse keywordSearchResponse = subject.search(request);
        BlogDocument blogDocument = keywordSearchResponse.documents().get(0);
        PageInfo pageInfo = keywordSearchResponse.pageInfo();

        then(searchEventPublisher).should().publish("keyword");
        assertThat(keywordSearchResponse).isNotNull();
        assertThat(blogDocument).isNotNull();
        assertThat(pageInfo).isNotNull();
    }

    private KeywordSearchRequest createKeywordSearchRequest() {
        return new KeywordSearchRequest("keyword", null, null, null);
    }

    private KeywordSearchResponse createKeywordSearchResponse() {
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