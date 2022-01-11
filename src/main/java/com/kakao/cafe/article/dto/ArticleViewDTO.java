package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import lombok.Getter;

import java.time.format.DateTimeFormatter;


@Getter
public class ArticleViewDTO {

    private String writer;
    private String title;
    private String writingTime;
    private Long countOfComment;

    public ArticleViewDTO(Article article) {
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.writingTime = article.getWritingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.countOfComment = article.getCountOfComment();
    }
}
