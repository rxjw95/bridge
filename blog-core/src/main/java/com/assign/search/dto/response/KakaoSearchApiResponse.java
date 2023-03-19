package com.assign.search.dto.response;

import com.assign.search.vo.BlogDocument;
import com.assign.search.vo.PageInfo;
import java.util.List;
import lombok.Getter;

@Getter
public class KakaoSearchApiResponse {

    private List<BlogDocument> documents;
    private PageInfo meta;

    public KakaoSearchApiResponse(List<BlogDocument> documents, PageInfo meta) {
        this.documents = documents;
        this.meta = meta;
    }

    protected KakaoSearchApiResponse() {
    }
}
