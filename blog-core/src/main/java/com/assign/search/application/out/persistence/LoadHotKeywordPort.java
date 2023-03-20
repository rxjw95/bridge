package com.assign.search.application.out.persistence;

import com.assign.search.domain.Keyword;
import java.util.List;

public interface LoadHotKeywordPort {

    List<Keyword> loadKeywordTopTen();

}
