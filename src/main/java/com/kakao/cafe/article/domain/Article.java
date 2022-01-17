package com.kakao.cafe.article.domain;

import com.kakao.cafe.exception.ArticleException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.user.domain.UserId;
import lombok.Getter;

import java.util.Date;

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

    public Long getArticleId() {
        return articleId;
    }

    public String getWriterId() {
        return writerId.getUserId();
    }

    public String getTitle() {
        return title.getTitle();
    }

    public String getContents() {
        return contents.getContents();
    }
}
