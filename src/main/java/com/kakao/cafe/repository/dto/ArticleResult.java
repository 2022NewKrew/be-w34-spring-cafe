package com.kakao.cafe.repository.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleResult {
    private Long postId;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long numOfComment;
}
