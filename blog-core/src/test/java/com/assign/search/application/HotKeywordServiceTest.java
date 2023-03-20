package com.assign.search.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.assign.search.application.out.persistence.LoadHotKeywordPort;
import com.assign.search.domain.Keyword;
import com.assign.search.dto.response.HotKeywordResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

class HotKeywordServiceTest {

    private final HotKeywordService hotKeywordService;

    HotKeywordServiceTest() {
        this.hotKeywordService = new HotKeywordService(new LoadHotKeywordPortStub());
    }

    @Test
    void find() {
        List<HotKeywordResponse> responses = hotKeywordService.find();

        assertThat(responses.get(0).keyword()).isEqualTo("대한민국");
        assertThat(responses.get(0).frequency()).isEqualTo(10);
        assertThat(responses.get(1).keyword()).isEqualTo("만세");
        assertThat(responses.get(1).frequency()).isEqualTo(5);
    }

    public static class LoadHotKeywordPortStub implements LoadHotKeywordPort {

        @Override
        public List<Keyword> loadKeywordTopTen() {
            return List.of(new Keyword("대한민국", 10), new Keyword("만세", 5));
        }
    }
}