package com.kakao.cafe.article.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.cafe.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
public class Article {

    private final String id;
    private final User author;
    private final String title;
    private final String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    public boolean isSameArticleById(String articleId) {
        return Objects.equals(this.id, articleId);
    }
}
