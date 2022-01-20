package com.kakao.cafe.article.dto.response;

import java.time.format.DateTimeFormatter;

import lombok.Getter;

import com.kakao.cafe.article.entity.Article;

@Getter
public class ArticleFindResponseDTO {
    private final int id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createTime;

    public ArticleFindResponseDTO(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.createTime = article.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
