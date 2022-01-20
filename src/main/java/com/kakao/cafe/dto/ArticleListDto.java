package com.kakao.cafe.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Comparator;

@Getter
public class ArticleListDto implements Comparable<ArticleListDto>{
    private final Long number;
    private final String id;
    private final String name;
    private final String title;
    private final Timestamp timestamp;

    public ArticleListDto(Long number, String id, String name, String title, Timestamp timestamp) {
        validate(number, id, name, title, timestamp);
        this.number = number;
        this.id = id;
        this.name = name;
        this.title = title;
        this.timestamp = timestamp;
    }

    private void validate(Long number, String id, String name, String title, Timestamp timestamp) {
    }

    @Override
    public int compareTo(ArticleListDto article) {
        return this.getNumber() > article.getNumber() ? 1 : -1;
    }
}
