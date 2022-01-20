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
public class Article {

    private final Long id;

    private final String uid;
    private final String title;
    private final String body;

    private final LocalDateTime createdAt;

    @Builder
    public Article(@Nullable Long id, @NotBlank String uid, @NotBlank String title,
        @NotBlank String body, @NotNull LocalDateTime createdAt) {
        this.id = id;
        this.uid = uid;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }
}
