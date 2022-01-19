package com.kakao.cafe.article.domain;

import com.kakao.cafe.common.exception.ForbiddenException;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Comment {

    private Long id;
    private String body;
    private LocalDateTime createdAt;
    private Long authorId;
    private Long articleId;

    public void validate(Long userId, Long articleId) {
        if (!Objects.equals(this.authorId, userId) || !Objects.equals(this.articleId, articleId)) {
            throw new ForbiddenException();
        }
    }
}
