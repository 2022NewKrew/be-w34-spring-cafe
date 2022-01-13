package com.kakao.cafe.domain;

import com.kakao.cafe.dto.CreateArticleDto;

import java.util.concurrent.atomic.AtomicInteger;

public class Article {
    private static final AtomicInteger articleCnt = new AtomicInteger(3);
    private final long id;
    private final String writer;
    private final String title;
    private final String contents;

    public Article(String writer, String title, String contents) {
        id = articleCnt.get();
        articleCnt.set((int) (id + 1));
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Article(CreateArticleDto createArticleDto) {
        id = articleCnt.get();
        articleCnt.set((int) (id + 1));
        this.writer = createArticleDto.getWriter();
        this.title = createArticleDto.getTitle();
        this.contents = createArticleDto.getContents();
    }

    public long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
