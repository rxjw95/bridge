package com.assign.search.application;

import com.assign.search.application.out.persistence.LoadKeywordPort;
import com.assign.search.application.out.persistence.SaveKeywordPort;
import com.assign.search.domain.Keyword;
import java.util.Optional;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    @EventListener
    @Transactional
    public void handleEvent(String keyword) {
        Optional<Keyword> findKeyword = loadKeywordPort.loadOneWithLock(keyword);

        if (findKeyword.isEmpty()) {
            saveSyncWithDoubleCheck(keyword);
            return;
        }

        findKeyword.get().addFrequency(1);
    }

    private void saveSyncWithDoubleCheck(String keyword) {
        synchronized (this) {
            Optional<Keyword> findKeyword = loadKeywordPort.loadOneWithLock(keyword);
            if (findKeyword.isEmpty()) {
                saveKeywordPort.saveAndFlush(new Keyword(keyword, 1));
                return;
            }
            findKeyword.get().addFrequency(1);
        }

    }
}
