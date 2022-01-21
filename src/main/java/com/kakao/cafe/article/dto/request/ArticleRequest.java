package com.kakao.cafe.article.dto.request;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;

public class ArticleRequest {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    private ArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toArticle(User user) {
        return new Article(null, this.title, this.content, user, 0L, LocalDateTime.now());
    }

    public Article toArticle(Long id) {
        return new Article(id, this.title, this.content, null, null, null);
    }
}
