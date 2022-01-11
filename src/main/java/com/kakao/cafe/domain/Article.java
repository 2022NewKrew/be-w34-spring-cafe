package com.kakao.cafe.domain;

import com.kakao.cafe.controller.dto.ArticleDto;
import com.kakao.cafe.repository.dto.ArticleResult;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class Article {
    private Long articleId;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long numOfComment;

    private Article() {}

    public static Article from(ArticleDto dto) {
        Article article = new Article();

        article.setContent(dto.getContent());
        article.setTitle(dto.getTitle());
        article.setWriter(dto.getWriter());
        article.setCreatedAt(LocalDateTime.now());
        article.setNumOfComment(0L);

        return article;
    }

    public static Article from(ArticleResult dto) {
        Article article = new Article();

        article.setArticleId(dto.getPostId());
        article.setContent(dto.getContent());
        article.setTitle(dto.getTitle());
        article.setWriter(dto.getWriter());
        article.setCreatedAt(LocalDateTime.now());
        article.setNumOfComment(dto.getNumOfComment());

        return article;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, writer, title, content, createdAt, numOfComment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        Article article = (Article) obj;

        return Objects.equals(writer, article.getWriter()) &&
                Objects.equals(createdAt, article.getCreatedAt()) &&
                Objects.equals(content, article.getContent()) &&
                Objects.equals(numOfComment, article.getNumOfComment());
    }
}
