package com.kakao.cafe.persistence.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString
@Getter
public class Reply {

    private final Long id;

    private final Long articleId;
    private final String uid;
    private final String userName;
    private final String body;

    private final LocalDateTime createdAt;

    @Builder
    public Reply(@Nullable Long id, @NotNull Long articleId, @NotBlank String uid,
        @NotBlank String userName, @NotBlank String body, @NotNull LocalDateTime createdAt) {
        this.id = id;
        this.articleId = articleId;
        this.uid = uid;
        this.userName = userName;
        this.body = body;
        this.createdAt = createdAt;
    }
}
