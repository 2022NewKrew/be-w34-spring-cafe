package com.kakao.cafe.dto.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArticleDto {
    private long id;
    private String author;
    private long userId;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private int views;

    public String formattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
    }
}
