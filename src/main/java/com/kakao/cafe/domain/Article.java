package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Article {

    private int id;
    private String author;
    private LocalDateTime createdAt;
    private String title;
    private String content;

}
