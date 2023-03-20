package com.assign.search.persentation;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.assign.search.application.in.usecase.FindHotKeywordUseCase;
import com.assign.search.application.in.usecase.SearchKeywordUseCase;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.HotKeywordResponse;
import com.assign.search.dto.response.KeywordSearchResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/blog")
@RestController
public class BlogSearchController {

    private final SearchKeywordUseCase searchKeywordUseCase;
    private final FindHotKeywordUseCase findHotKeywordUseCase;

    public BlogSearchController(SearchKeywordUseCase searchKeywordUseCase,
        FindHotKeywordUseCase findHotKeywordUseCase) {
        this.searchKeywordUseCase = searchKeywordUseCase;
        this.findHotKeywordUseCase = findHotKeywordUseCase;
    }

    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    public KeywordSearchResponse search(@ModelAttribute KeywordSearchRequest request) {
        return searchKeywordUseCase.search(request);
    }

    @GetMapping(value = "/keyword/hot")
    public List<HotKeywordResponse> getHotKeyword() {
        return findHotKeywordUseCase.find();
    }
}
