package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;

public class ArticleVO {

    private final int id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createdAt;

    public ArticleVO(int id, String writer, String title, String contents, String createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public static ArticleVO from(Article article) {
        return new ArticleVO(
            article.getId(),
            article.getWriter(),
            article.getTitle(),
            article.getContents(),
            article.getCreatedAt()
        );
    }

    public Article toEntity() throws IllegalWriterException, IllegalTitleException, IllegalDateException {
        Article article = new Article.Builder().writer(this.writer)
                                               .title(this.title).
                                               contents(this.contents)
                                               .createdAt(this.createdAt)
                                               .build();
        article.setId(this.id);
        return article;
    }

    public int getId() {
        return id;
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

    public String getCreatedAt() {
        return createdAt;
    }
}
