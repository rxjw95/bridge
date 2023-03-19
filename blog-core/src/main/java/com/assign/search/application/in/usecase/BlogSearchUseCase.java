package com.assign.search.application.in.usecase;

import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;

public interface BlogSearchUseCase {

  KeywordSearchResponse search(KeywordSearchRequest request);
}
