package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.QuestionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class Article {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private String writingTime;
    private Long countOfComment;

    public Article(Long id, QuestionDTO questionDTO) {
        this.id = id;
        this.writer = questionDTO.getWriter();
        this.contents = questionDTO.getContents();
        this.title = questionDTO.getTitle();
        this.writingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.countOfComment = 0L;
    }
}
