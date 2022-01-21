package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleSaveForm;
import com.kakao.cafe.domain.user.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
public class Article {
    private Long articleId;
    private Long authorId;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long numOfComment;


    @Override
    public int hashCode() {
        return Objects.hash(articleId, author, title, content, createdAt, numOfComment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        Article article = (Article) obj;

        return Objects.equals(author, article.getAuthor()) &&
                Objects.equals(createdAt, article.getCreatedAt()) &&
                Objects.equals(content, article.getContent()) &&
                Objects.equals(numOfComment, article.getNumOfComment());
    }
}
