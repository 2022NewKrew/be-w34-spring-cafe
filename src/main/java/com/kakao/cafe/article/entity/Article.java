package com.kakao.cafe.article.entity;

import com.kakao.cafe.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class Article {

    private Long id;
    private User writer;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
}
