package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ArticleResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String createDate;

    public ArticleResponseDTO(Article article, String author) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = author;
        this.createDate = article.getCreateDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
