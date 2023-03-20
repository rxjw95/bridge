package com.assign.search.application;

import com.assign.search.application.out.persistence.LoadKeywordPort;
import com.assign.search.application.out.persistence.SaveKeywordPort;
import com.assign.search.domain.Keyword;
import java.util.Optional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KeywordSearchEventListener {

    private final LoadKeywordPort loadKeywordPort;
    private final SaveKeywordPort saveKeywordPort;

    public KeywordSearchEventListener(
        LoadKeywordPort loadKeywordPort,
        SaveKeywordPort saveKeywordPort) {
        this.loadKeywordPort = loadKeywordPort;
        this.saveKeywordPort = saveKeywordPort;
    }

    @EventListener
    @Transactional
    public void handleEvent(String keyword) {
        Optional<Keyword> findKeyword = loadKeywordPort.loadOne(keyword);

        if (findKeyword.isPresent()) {
            findKeyword.get().addFrequency(1);
            return;
        }

        saveKeywordPort.save(new Keyword(keyword, 1));
    }
}
