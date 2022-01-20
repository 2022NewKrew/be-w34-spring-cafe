package com.kakao.cafe.service.article.dto;

import com.kakao.cafe.model.article.Article;
import java.time.LocalDateTime;

public class ArticleDto {

    private final int id;
    private final String title;
    private final String writer;
    private final String contents;
    private final LocalDateTime createDate;

    public ArticleDto(int id, String title, String writer, String contents,
            LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createDate = createDate;
    }

    public ArticleDto(Article article) {
        this(
                article.getId(),
                article.getTitle().getValue(),
                article.getWriter().getValue(),
                article.getContents().getValue(),
                article.getCreateDate()
        );
    }

    public String getWriter() {
        return writer;
    }
}
