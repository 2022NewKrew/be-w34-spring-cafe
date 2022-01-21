package com.kakao.cafe.service.article.dto;

import com.kakao.cafe.model.article.Article;
import java.time.LocalDateTime;

public class ArticleDto {

    private final int id;
    private final String title;
    private final String userId;
    private final String contents;
    private final LocalDateTime createDate;

    public ArticleDto(int id, String title, String userId, String contents,
            LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.contents = contents;
        this.createDate = createDate;
    }

    public ArticleDto(Article article) {
        this(
                article.getId(),
                article.getTitle().getValue(),
                article.getUserId().getValue(),
                article.getContents().getValue(),
                article.getCreateDate()
        );
    }

    public String getUserId() {
        return userId;
    }
}
