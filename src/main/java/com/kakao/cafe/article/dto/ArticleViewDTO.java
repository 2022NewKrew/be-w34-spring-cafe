package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;



public class ArticleViewDTO {

    private Long id;
    private String writer;
    private String title;
    private String writingTime;
    private Long countOfComment;

    public ArticleViewDTO(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.writingTime = article.getWritingTime();
        this.countOfComment = article.getCountOfComment();
    }
}
