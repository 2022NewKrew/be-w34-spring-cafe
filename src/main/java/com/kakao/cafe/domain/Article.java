package com.kakao.cafe.domain;

import com.kakao.cafe.dto.CreateArticleDto;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Article {
    private static final AtomicInteger articleCnt = new AtomicInteger(1);
    private final long id;
    private final LocalDateTime writeTime;
    private final String writer;
    private final String title;
    private final String contents;


    public Article(CreateArticleDto createArticleDto) {
        id = articleCnt.get();
        articleCnt.set((int) (id + 1));
        writeTime = LocalDateTime.now();
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

    public LocalDateTime getWriteTime() {
        return writeTime;
    }
}
