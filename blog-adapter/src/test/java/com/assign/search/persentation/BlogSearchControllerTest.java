package com.assign.search.persentation;

import static com.assign.search.docs.BlogSearchRestDocument.searchKeywordRestDocument;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assign.search.application.in.usecase.BlogSearchUseCase;
import com.assign.search.dto.BlogDocument;
import com.assign.search.dto.PageInfo;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest
class BlogSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension(
        "build/generated-snippets");

    @MockBean
    private BlogSearchUseCase blogSearchUseCase;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .addFilters(new CharacterEncodingFilter("UTF-8"))
            .build();
    }

    @Test
    void search() throws Exception {
        final String restKeyDummy = "KakaoAK 1029384756abc";

        KeywordSearchRequest request = createKeywordSearchRequest();

        List<BlogDocument> blogDocuments = createDummyBlogDocuments();
        PageInfo pageInfo = createDummyPageInfo();
        KeywordSearchResponse response = new KeywordSearchResponse(blogDocuments, pageInfo);

        given(blogSearchUseCase.search(request)).willReturn(response);

        mockMvc.perform(get("/blog/search")
                .accept(APPLICATION_JSON)
                .header(AUTHORIZATION, restKeyDummy)
                .param("query", "keyword")
            )
            .andExpect(status().isOk())
            .andDo(print())
            .andDo(searchKeywordRestDocument());
    }

    private KeywordSearchRequest createKeywordSearchRequest() {
        return new KeywordSearchRequest("keyword", null, null, null);
    }

    private List<BlogDocument> createDummyBlogDocuments() {
        BlogDocument blogDocument = BlogDocument.of(
            "블로그 명",
            "블로그 컨텐츠",
            LocalDateTime.of(2023, 3, 18, 10, 0),
            "https://search3.kakaocdn.net",
            "게시글 제목",
            "https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog");

        return List.of(blogDocument);
    }

    private PageInfo createDummyPageInfo() {
        return new PageInfo(100, 50, false);
    }

}