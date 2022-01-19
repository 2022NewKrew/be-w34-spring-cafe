package com.kakao.cafe.domain.article;

import com.kakao.cafe.web.dto.ArticleDTO;
import lombok.Getter;

@Getter
public class Article {
    private final long id;
    private final String title;
    private final String content;
    private final String createUserId;
    private final String createDate;
    private final int views;


    public Article(ArticleDTO articleDTO) {
        this.id = 0;
        this.title = articleDTO.getTitle();
        this.content = articleDTO.getContent();
        this.createUserId = articleDTO.getCreateUserId();
        this.createDate = articleDTO.getCreateDate();
        this.views = articleDTO.getViews();
    }

    private Article(long id, String title, String content, String createUserId, String createDate, int views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.views = views;
    }

    public static Article newInstance(long id, String title, String content, String createUserId, String createDate, int views) {
        return new Article(id, title, content, createUserId, createDate, views);
    }

}
