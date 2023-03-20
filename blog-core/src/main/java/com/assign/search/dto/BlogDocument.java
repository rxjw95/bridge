package com.assign.search.dto;

import lombok.Getter;

@Getter
public class BlogDocument {

    private String blogName;
    private String contents;
    private String dateTime;
    private String thumbnail;
    private String title;
    private String url;

    public static BlogDocument of(String blogName, String contents, String dateTime,
        String thumbnail, String title, String url) {
        return new BlogDocument(blogName, contents, dateTime, thumbnail, title, url);
    }

    private BlogDocument(String blogName, String contents, String dateTime,
        String thumbnail, String title, String url) {
        this.blogName = blogName;
        this.contents = contents;
        this.dateTime = dateTime;
        this.thumbnail = thumbnail;
        this.title = title;
        this.url = url;
    }

    protected BlogDocument() {
    }
}
