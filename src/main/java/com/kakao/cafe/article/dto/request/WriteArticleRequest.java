package com.kakao.cafe.article.dto.request;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WriteArticleRequest {

    @NotNull
    private final Long userId;

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    private WriteArticleRequest(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Article toArticle(User user) {
        return new Article(null, this.title, this.content, user, 0L, LocalDateTime.now());
    }

    public Long getUserId() {
        return userId;
    }
}
