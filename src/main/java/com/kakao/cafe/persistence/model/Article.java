package com.kakao.cafe.persistence.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString
@Getter
@Builder
public class Article {

    @Nullable
    private final Long id;

    @NotBlank
    private final String uid;
    @NotBlank
    private final String title;
    @NotBlank
    private final String body;

    @Nullable
    private final LocalDateTime createdAt;
}
