package com.assign.search.infrastructure.persistence;

import com.assign.search.application.out.persistence.LoadHotKeywordPort;
import com.assign.search.application.out.persistence.LoadKeywordPort;
import com.assign.search.application.out.persistence.SaveKeywordPort;
import com.assign.search.domain.Keyword;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class KeywordJpaAdapter implements LoadKeywordPort, LoadHotKeywordPort, SaveKeywordPort {

    private final KeywordRepository keywordRepository;

    public KeywordJpaAdapter(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Override
    public Optional<Keyword> loadOneWithLock(String keyword) {
        return keywordRepository.findByIdWithLock(keyword);
    }

    @Override
    public List<Keyword> loadKeywordTopTen() {
        return keywordRepository.findTopTenKeyword();
    }

    @Override
    public void saveAndFlush(Keyword keyword) {
        keywordRepository.saveAndFlush(keyword);
    }
}
