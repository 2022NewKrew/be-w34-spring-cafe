package com.kakao.cafe.qna.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleDTO {
    private final String writer;
    private final String writerProfileAddress;
    private final String writtenTime;
    private final String content;
    private final String title;
    private final int articleId;
}
