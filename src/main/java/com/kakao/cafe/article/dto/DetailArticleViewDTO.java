package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;

public class DetailArticleViewDTO {

    private Long id;
    private String writer;
    private String title;
    private String writingTime;
    private Long countOfComment;
    private String contents;

    public DetailArticleViewDTO(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.writingTime = article.getWritingTime();
        this.countOfComment = article.getCountOfComment();
        this.contents = article.getContents();
    }
}
