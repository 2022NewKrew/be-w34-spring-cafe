package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Article {
    private long key;
    private String author;
    private String title;
    private String content;
    private LocalDateTime postTime;
}
