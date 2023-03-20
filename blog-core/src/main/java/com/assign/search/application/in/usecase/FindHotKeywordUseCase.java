package com.assign.search.application.in.usecase;

import com.assign.search.dto.response.HotKeywordResponse;
import java.util.List;

public interface FindHotKeywordUseCase {

    List<HotKeywordResponse> find();
}
