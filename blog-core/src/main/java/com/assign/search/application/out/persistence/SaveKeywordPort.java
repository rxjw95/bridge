package com.assign.search.application.out.persistence;

import com.assign.search.domain.Keyword;

public interface SaveKeywordPort {

    void saveAndFlush(Keyword keyword);

}
