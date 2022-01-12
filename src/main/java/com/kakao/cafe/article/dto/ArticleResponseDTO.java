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

    public ArticleResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getAuthor();
        this.createDate = article.getCreateDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
