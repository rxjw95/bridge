package com.assign.search.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.assign.search.domain.Keyword;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class KeywordRepositoryTest {

    @Autowired
    private KeywordRepository keywordRepository;

    private final List<String> names = List.of("b", "g", "h", "i", "e");

    @Test
    void findTopTenKeyword() {
        for (int i = 1; i <= 5; i++) {
            keywordRepository.save(new Keyword(names.get(i - 1), i));
        }

        List<Keyword> topTenKeyword = keywordRepository.findTopTenKeyword();
        assertThat(topTenKeyword.get(0).getKeyword()).isEqualTo("e");
        assertThat(topTenKeyword.get(1).getKeyword()).isEqualTo("i");
        assertThat(topTenKeyword.get(2).getKeyword()).isEqualTo("h");
        assertThat(topTenKeyword.get(3).getKeyword()).isEqualTo("g");
        assertThat(topTenKeyword.get(4).getKeyword()).isEqualTo("b");
    }

    @Test
    void findByIdWithLock() {
        Keyword keyword = new Keyword("카카오", 1);
        keywordRepository.save(keyword);

        Optional<Keyword> findKeyword = keywordRepository.findByIdWithLock(keyword.getKeyword());

        assertThat(findKeyword).contains(keyword);
    }

    @Test
    void saveAndFlush() {
        Keyword keyword = new Keyword("카카오", 1);

        Keyword savedKeyword = keywordRepository.saveAndFlush(keyword);

        Optional<Keyword> findKeyword = keywordRepository.findById(savedKeyword.getKeyword());
        assertThat(findKeyword).isPresent();
    }

}