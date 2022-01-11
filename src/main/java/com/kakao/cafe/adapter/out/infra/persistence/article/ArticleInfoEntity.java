package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.time.LocalDateTime;

public class ArticleInfoEntity {

    private final int index;
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime postedDate;

    public ArticleInfoEntity(int index, String writer, String title, String contents,
        LocalDateTime postedDate) {
        this.index = index;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.postedDate = postedDate;
    }

    static ArticleInfoEntity from(Article article) {
        return new ArticleInfoEntity(article.getIndex(), article.getWriter(), article.getTitle(),
            article.getContents(), article.getPostedDate());
    }

    public int getIndex() {
        return index;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }
}
