package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ShowArticleDto {
    private final long id;
    private final String writeTime;
    private final String writer;
    private final String title;
    private final String contents;
    private final String span;

    public ShowArticleDto(Article article) {
        id = article.getId();
        writeTime = timeToString(article.getWriteTime());
        writer = article.getWriter();
        title = article.getTitle();
        contents = article.getContents();
        span = timeSpan(article.getWriteTime());
    }

    private static String timeToString(LocalDateTime date) {
        DateTimeFormatter formatType = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        return date.format(formatType);
    }

    private static String timeSpan(LocalDateTime date) {
        DateTimeFormatter formatType = DateTimeFormatter.ofPattern("dd");
        Period span = Period.between(LocalDate.now(), LocalDate.from(date));
        return String.valueOf(span.getDays());
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

    public String getWriteTime() {
        return writeTime;
    }
}
