package com.assign.search.application;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.assign.search.application.out.persistence.LoadKeywordPort;
import com.assign.search.application.out.persistence.SaveKeywordPort;
import com.assign.search.domain.Keyword;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KeywordSearchEventListenerTest {

    @InjectMocks
    private KeywordSearchEventListener listener;

    @Mock
    private LoadKeywordPort loadKeywordPort;

    @Mock
    private SaveKeywordPort saveKeywordPort;

    @Test
    void handleEvent_NotFoundKeyword_Save() {
        final String keyword = "카카오";
        given(loadKeywordPort.loadOne(keyword)).willReturn(Optional.empty());

        listener.handleEvent(keyword);

        then(saveKeywordPort).should().save(new Keyword(keyword, 1));
    }

    @Test
    void handleEvent_ExistKeyword_Update() {
        final String keyword = "카카오";
        Keyword keywordEntity = new Keyword(keyword, 1);
        given(loadKeywordPort.loadOne(keyword)).willReturn(Optional.of(keywordEntity));

        listener.handleEvent(keyword);

        Assertions.assertThat(keywordEntity.getSearchFrequency()).isEqualTo(2);
    }

}