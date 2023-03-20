package com.assign.search.infrastructure.persistence;

import com.assign.search.application.out.persistence.LoadHotKeywordPort;
import com.assign.search.domain.Keyword;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class KeywordJpaAdapter implements LoadHotKeywordPort {

    private final KeywordRepository keywordRepository;

    public KeywordJpaAdapter(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Override
    public List<Keyword> loadKeywordTopTen() {
        return keywordRepository.findTopTenKeyword();
    }

}
