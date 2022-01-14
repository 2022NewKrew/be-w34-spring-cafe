package com.kakao.cafe.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Comparator;

@Getter
public class ArticleListDto implements Comparable<ArticleListDto>{
    private final Long number;
    private final String writer;
    private final String title;
    private final Timestamp timestamp;

    public ArticleListDto(Long number, String writer, String title, Timestamp timestamp) {
        validate(number, writer, title, timestamp);
        this.number = number;
        this.writer = writer;
        this.title = title;
        this.timestamp = timestamp;
    }

    private void validate(Long number, String writer, String title, Timestamp timestamp) {
    }

    @Override
    public int compareTo(ArticleListDto article) {
        return this.getNumber() > article.getNumber() ? 1 : -1;
    }
}
