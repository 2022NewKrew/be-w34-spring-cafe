package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Title;

import java.time.LocalDateTime;

public class ArticleResponse {
    private final int articleId;
    private final Title title;
    private final LocalDateTime createdAt;

    public ArticleResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.createdAt = article.getCreatedAt();
    }

    public int getArticleId() {
        return articleId;
    }

    public Title getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(articleId);
        result = 31 * result + title.hashCode();
        result = 31 * result + createdAt.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof ArticleResponse)) {
            return false;
        }

        ArticleResponse other = (ArticleResponse) obj;
        return articleId == other.getArticleId() &&
                title.equals(other.getTitle()) &&
                createdAt.equals(other.getCreatedAt());
    }
}
