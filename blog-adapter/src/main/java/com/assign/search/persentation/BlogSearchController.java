package com.assign.search.persentation;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.assign.search.application.in.usecase.SearchKeywordUseCase;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/blog")
@RestController
public class BlogSearchController {

    private final SearchKeywordUseCase searchKeywordUseCase;

    public BlogSearchController(SearchKeywordUseCase searchKeywordUseCase) {
        this.searchKeywordUseCase = searchKeywordUseCase;
    }

    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE, headers = "charset=utf-8")
    public KeywordSearchResponse search(@ModelAttribute KeywordSearchRequest request) {
        return searchKeywordUseCase.search(request);
    }

}
