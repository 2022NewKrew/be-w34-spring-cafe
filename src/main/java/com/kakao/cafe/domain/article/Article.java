package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.exception.AuthorNotMatchException;
import com.kakao.cafe.domain.base.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
public class Article extends BaseEntity {
    private Long userId;
    private String title;
    private String content;
    private Long viewCount;

    @Builder
    public Article(Long id, Long userId, String title, String content, Long viewCount, LocalDateTime createdAt, Boolean isDeleted) {
        super(id, createdAt, isDeleted);
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }

    public void updateInfo(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }

    public void validateAuthor(Long userId) {
        if (!getUserId().equals(userId)) throw new AuthorNotMatchException();
    }
}
