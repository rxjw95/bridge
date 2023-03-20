package com.assign.search.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class KeywordTest {

    @Test
    void addFrequency() {
        Keyword keyword = new Keyword("류장욱", 1);
        keyword.addFrequency(1);

        Assertions.assertThat(keyword.getSearchFrequency()).isEqualTo(2);
    }
}