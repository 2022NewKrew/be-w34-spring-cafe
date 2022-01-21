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
        return Article.builder()
            .title(this.title)
            .content(this.content)
            .user(user)
            .viewNum(0L)
            .createdDate(LocalDateTime.now())
            .build();
    }

    public Article toArticle(Long id) {
        return Article.builder()
            .id(id)
            .title(this.title)
            .content(this.content)
            .build();
    }
}
