package com.kakao.cafe.domain;

import com.kakao.cafe.dto.CreateArticleDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Article {
    private static final AtomicInteger articleCnt = new AtomicInteger(2);
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

    public Article(long id, LocalDateTime writeTime, String writer, String title, String contents) {
        this.id = id;
        this.writeTime = writeTime;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
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

    public String writeTimeToStr() {
        DateTimeFormatter formatType = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        return writeTime.format(formatType);
    }

    public String spanTimeToStr() {
        DateTimeFormatter formatType = DateTimeFormatter.ofPattern("dd");
        Period span = Period.between(LocalDate.from(writeTime), LocalDate.now());
        return String.valueOf(span.getDays());
    }
}
