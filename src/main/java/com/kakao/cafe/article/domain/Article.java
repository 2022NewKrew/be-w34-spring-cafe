package com.kakao.cafe.article.domain;

import com.kakao.cafe.common.exception.ForbiddenException;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Article {

    private Long id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private int viewCount;
    private Long authorId;

    public void validate(Long userId) {
        if (!Objects.equals(authorId, userId)) {
            throw new ForbiddenException();
        }
    }
}
