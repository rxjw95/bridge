package com.assign.search.application;

import com.assign.search.application.in.usecase.FindHotKeywordUseCase;
import com.assign.search.application.out.persistence.LoadHotKeywordPort;
import com.assign.search.domain.Keyword;
import com.assign.search.dto.response.HotKeywordResponse;
import java.util.List;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class HotKeywordService implements FindHotKeywordUseCase {

    private final LoadHotKeywordPort loadHotKeywordPort;

    public HotKeywordService(LoadHotKeywordPort loadHotKeywordPort) {
        this.loadHotKeywordPort = loadHotKeywordPort;
    }


    @Override
    public List<HotKeywordResponse> find() {
        List<Keyword> keywords = loadHotKeywordPort.loadKeywordTopTen();

        return keywords.stream()
            .map(convertFunc())
            .toList();
    }

    private Function<Keyword, HotKeywordResponse> convertFunc() {
        return keyword -> new HotKeywordResponse(
            keyword.getKeyword(),
            keyword.getSearchFrequency());
    }
}
