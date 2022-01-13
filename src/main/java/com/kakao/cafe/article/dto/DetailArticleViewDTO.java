package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;

public class DetailArticleViewDTO {

    private final Long id;
    private final String writer;
    private final String title;
    private final String writingTime;
    private final Long countOfComment;
    private final String contents;

    public DetailArticleViewDTO(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.writingTime = article.getWritingTime();
        this.countOfComment = article.getCountOfComment();
        this.contents = article.getContents();
    }
}
