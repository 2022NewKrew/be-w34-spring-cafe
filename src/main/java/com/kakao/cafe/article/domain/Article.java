package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.exception.ArticleException;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Getter
public class Article {
    private final Long articleId;
    private final UserId writerId;
    private final Date writeTime;
    private final Title title;
    private final Contents contents;

    public Article(Long articleId, UserId writerId, Date writeTime, Title title, Contents contents) {
        this.articleId = articleId;
        this.writerId = writerId;
        this.writeTime = writeTime;
        this.title = title;
        this.contents = contents;
    }

    public Article(UserId writerId, Date writeTime, Title title, Contents contents) {
        articleId = 0L;
        this.writerId = writerId;
        this.writeTime = writeTime;
        this.title = title;
        this.contents = contents;
    }

    public Article(Long articleId, Article article) {
        validateNullArticle(article);
        this.articleId = articleId;
        writerId = article.writerId;
        writeTime = article.writeTime;
        title = article.title;
        contents = article.contents;
    }

    private void validateNullArticle(Article article) {
        if (article == null) {
            throw new ArticleException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Article article = (Article) object;
        return Objects.equals(articleId, article.articleId) &&
                Objects.equals(writerId, article.writerId) &&
                Objects.equals(writeTime, article.writeTime) &&
                Objects.equals(title, article.title) &&
                Objects.equals(contents, article.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, writerId, writeTime, title, contents);
    }
}
