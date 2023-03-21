package com.assign.search.application.out.persistence;

import com.assign.search.domain.Keyword;
import java.util.Optional;

public interface LoadKeywordPort {

    Optional<Keyword> loadOneWithLock(String keyword);
}
