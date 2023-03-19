package com.assign.search.persentation;

import com.assign.search.application.in.usecase.BlogSearchUseCase;
import com.assign.search.dto.request.KeywordSearchRequest;
import com.assign.search.dto.response.KeywordSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/blog")
@RestController
public class BlogSearchController {

  private final BlogSearchUseCase blogSearchUseCase;

  public BlogSearchController(BlogSearchUseCase blogSearchUseCase) {
    this.blogSearchUseCase = blogSearchUseCase;
  }

  @GetMapping("/search")
  public KeywordSearchResponse search(@ModelAttribute KeywordSearchRequest request) {
    return blogSearchUseCase.search(request);
  }
}
