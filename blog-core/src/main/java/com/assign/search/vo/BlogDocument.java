package com.assign.search.vo;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BlogDocument {

  private final String blogName;
  private final String contents;
  private final LocalDateTime localDateTime;
  private final String thumbnail;
  private final String title;
  private final String url;

  public static BlogDocument of(String blogName, String contents, LocalDateTime localDateTime,
      String thumbnail, String title, String url) {
    return new BlogDocument(blogName, contents, localDateTime, thumbnail, title, url);
  }

  private BlogDocument(String blogName, String contents, LocalDateTime localDateTime,
      String thumbnail, String title, String url) {
    this.blogName = blogName;
    this.contents = contents;
    this.localDateTime = localDateTime;
    this.thumbnail = thumbnail;
    this.title = title;
    this.url = url;
  }

}
