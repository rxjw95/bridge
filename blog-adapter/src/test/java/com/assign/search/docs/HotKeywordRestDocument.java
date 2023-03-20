package com.assign.search.docs;

import static com.assign.search.docs.RestApiDocumentUtils.getDocumentRequest;
import static com.assign.search.docs.RestApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

public class HotKeywordRestDocument {

    public static RestDocumentationResultHandler hotKeywordRestDocument() {
        return MockMvcRestDocumentation.document("blog-hot-keyword",
            getDocumentRequest(),
            getDocumentResponse(),
            responseFields(
                fieldWithPath("[].keyword").description("키워드"),
                fieldWithPath("[].frequency").description("빈도수").type("Integer")
            )
        );
    }
}
