package com.kakao.cafe.article.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class Article {

    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDate createDate;

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateDateString() {
        return createDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
