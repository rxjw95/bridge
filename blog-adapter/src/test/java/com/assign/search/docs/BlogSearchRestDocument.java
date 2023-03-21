package com.assign.search.docs;

import static com.assign.search.docs.RestApiDocumentUtils.getDocumentRequest;
import static com.assign.search.docs.RestApiDocumentUtils.getDocumentResponse;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

public class BlogSearchRestDocument {

    public static RestDocumentationResultHandler searchKeywordRestDocument() {
        return MockMvcRestDocumentation.document("blog-search",
            getDocumentRequest(),
            getDocumentResponse(),
            requestHeaders(headerWithName(AUTHORIZATION).description("REST API KEY")),
            queryParameters(
                parameterWithName("query").description(
                    "검색을 원하는 질의어\n 특정 블로그 글만 검색하고 싶은 경우, 블로그 url과 검색어를 공백(' ') 구분자로 넣을 수 있음"),
                parameterWithName("sort").optional()
                    .description("결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy"),
                parameterWithName("page").optional().description("결과 페이지 번호, 1~50 사이의 값, 기본 값 1"),
                parameterWithName("size").optional()
                    .description("한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10")
            ),
            responseFields(
                fieldWithPath("documents[].blogName").description("블로그 명"),
                fieldWithPath("documents[].contents").description("블로그 컨텐츠"),
                fieldWithPath("documents[].dateTime").description("게시글 작성 시간")
                    .type("DateTime"),
                fieldWithPath("documents[].thumbnail").description("썸네일"),
                fieldWithPath("documents[].title").description("게시글 제목"),
                fieldWithPath("documents[].url").description("블로그 링크"),
                fieldWithPath("pageInfo.totalCount").description("총 조회 게시물 수").type("Integer"),
                fieldWithPath("pageInfo.pageableCount").description("노출 가능한 게시물 수").type("Integer"),
                fieldWithPath("pageInfo.isLastPage").description("마지막 페이지 여부").type("Boolean")
            )
        );
    }
}
