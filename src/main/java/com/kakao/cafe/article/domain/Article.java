package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.QuestionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Article {
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime writingTime;
    private Long countOfComment;

    public Article(QuestionDTO questionDTO) {
        this.writer = questionDTO.getWriter();
        this.contents = questionDTO.getContents();
        this.title = questionDTO.getTitle();
        this.writingTime=LocalDateTime.now();
        this.countOfComment=0L;
    }
}
