package com.assign.search.integration;

import com.assign.search.domain.Keyword;
import com.assign.search.infrastructure.persistence.KeywordRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("test")
public class DBsetup {

    @Autowired
    private KeywordRepository keywordRepository;

    private final List<Keyword> keywords = List.of(
        new Keyword("대한민국", 42),
        new Keyword("만세", 125),
        new Keyword("연진아", 321),
        new Keyword("기상캐스터", 4),
        new Keyword("테니스", 12),
        new Keyword("카카오", 1111),
        new Keyword("골프", 123),
        new Keyword("네이버", 63),
        new Keyword("인생", 54),
        new Keyword("세상만사", 92),
        new Keyword("노노", 12)
    );

    @Transactional
    public void setUpKeyword() {
        keywordRepository.saveAll(keywords);
    }
}
