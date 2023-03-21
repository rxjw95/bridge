package com.assign.search.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity
@Getter
@EqualsAndHashCode
public class Keyword implements Persistable<String> {

    @Id
    private String keyword;
    private Integer searchFrequency;

    public Keyword(String keyword, Integer searchFrequency) {
        this.keyword = keyword;
        this.searchFrequency = searchFrequency;
    }

    public void addFrequency(int frequency) {
        searchFrequency += frequency;
    }

    @Override
    public String getId() {
        return keyword;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    protected Keyword() {
    }
}
